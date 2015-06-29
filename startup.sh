#!/bin/bash
chown -R tomcat7:tomcat7 /unified-views
chown -R tomcat7:tomcat7 /logs


# Database connection details
cd /config
ORIG_URL="database.sql.url  = jdbc:mysql:\/\/mysql:3306\/unified_views?characterEncoding=utf8"
ORIG_USER="database.sql.user = unified_views"
ORIG_PASSWORD="database.sql.password = unified_views"
URL="database.sql.url = jdbc:mysql:\/\/$MYSQL_HOST:$MYSQL_PORT\/$MYSQL_DATABASE?characterEncoding=utf8"
USER="database.sql.user = $MYSQL_USER"
PASSWORD="database.sql.password = $MYSQL_PASSWORD"
sed -i "s/$ORIG_URL/$URL/" *.properties
sed -i "s/$ORIG_USER/$USER/" *.properties
sed -i "s/$ORIG_PASSWORD/$PASSWORD/" *.properties

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

tail -f /var/log/tomcat7/catalina.out
