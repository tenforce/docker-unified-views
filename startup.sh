#!/bin/bash
chown -R tomcat7:tomcat7 /unified-views
chown -R tomcat7:tomcat7 /logs
chmod +x /add-dpu.sh
chmod +x /env-to-java-properties-file.sh

sh /env-to-java-properties-file.sh

# Unified Views backend
cd /unified-views
nohup java -DconfigFileLocation=/config/backend-config.properties -jar /packages/backend-2.1.0.jar &

# (optionally) download DPUs
DPU_DIR=/dpus
if [[ $DOWNLOAD_DPUS  && ! -e $DPU_DIR/.downloaded ]]; then
		pushd $DPU_DIR
		curl --output /$DPU_DIR/pom.xml --silent https://raw.githubusercontent.com/UnifiedViews/Plugins/$PLUGINS_VERSION_TAG/debian/pom.xml 
		# pom cleanup, to be removed later on
		VERSION_STRING=`grep "<version>.*</version>" pom.xml | head -n 1`
		echo $VERSION_STRING
		sed -n -i '1h;1!H;${g;s/<parent>.*<\/parent>//;p;}' pom.xml
		sed -i "s#<name>unifiedviews-plugins</name>#<name>unifiedviews-plugins</name>\n$VERSION_STRING#" pom.xml 
		# pom cleanup end
		  mvn dependency:copy-dependencies -DoutputDirectory=$DPU_DIR && date +%Y-%m-%dT%H:%M:%S%z > $DPU_DIR/.downloaded && rm $DPU_DIR/pom.xml
		popd 
fi
# Unified Views frontend
cp /packages/unifiedviews.war /var/lib/tomcat7/webapps/

# Unified Views API
cp /packages/master.war /var/lib/tomcat7/webapps/

# Start Tomcat
chmod +x /usr/share/tomcat7/bin/setenv.sh
service tomcat7 start

# Wait till Tomcat startup has finished and webapps are started (max 3 minutes)
i=0
until $(curl --output /dev/null --silent --head --fail --user $MASTER_USER:$MASTER_PASSWORD http://localhost:8080/master/api/1/pipelines) || [ "$i" -gt 36 ]; do
    i=$((i+1))
    printf '.'
    sleep 5
done

# Add DPUs
for f in /dpus/*.jar; do bash /add-dpu.sh "$f"; done

tail -f /var/log/tomcat7/catalina.out
