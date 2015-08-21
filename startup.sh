#!/bin/bash
chown -R tomcat7:tomcat7 /unified-views
chown -R tomcat7:tomcat7 /logs
chmod +x /add-dpu.sh
chmod +x /env-to-java-properties-file.sh

sh /env-to-java-properties-file.sh

# Unified Views backend
cd /unified-views
nohup java -DconfigFileLocation=/config/backend-config.properties -jar /packages/backend-2.1.0.jar &

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
