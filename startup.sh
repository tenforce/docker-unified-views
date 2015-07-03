#!/bin/bash
chown -R tomcat7:tomcat7 /unified-views
chown -R tomcat7:tomcat7 /logs
chmod +x /add-dpu.sh

# Database connection details
cd /config
ORIG_URL="database.sql.url"
ORIG_USER="database.sql.user"
ORIG_PASSWORD="database.sql.password"
URL="database.sql.url = jdbc:mysql:\/\/$MYSQL_HOST:$MYSQL_PORT\/$MYSQL_DATABASE?characterEncoding=utf8"
USER="database.sql.user = $MYSQL_USER"
PASSWORD="database.sql.password = $MYSQL_PASSWORD"
sed -i "s/^${ORIG_URL}.*/$URL/" *.properties
sed -i "s/^${ORIG_USER}.*/$USER/" *.properties
sed -i "s/^${ORIG_PASSWORD}.*/$PASSWORD/" *.properties

# Master credentials
cd /config
ORIG_USER="master.api.user"
ORIG_PASSWORD="master.api.password"
USER="master.api.user = $MASTER_USER"
PASSWORD="master.api.password = $MASTER_PASSWORD"
sed -i "s/^${ORIG_USER}.*/$USER/" *.properties
sed -i "s/^${ORIG_PASSWORD}.*/$PASSWORD/" *.properties

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
