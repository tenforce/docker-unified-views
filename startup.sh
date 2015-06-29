#!/bin/bash
chown -R tomcat7:tomcat7 /unified-views
chown -R tomcat7:tomcat7 /logs
cd /unified-views

nohup java -DconfigFileLocation=/config/backend-config.properties -jar /packages/backend-2.1.0.jar &

cp /packages/unifiedviews.war /var/lib/tomcat7/webapps/
chmod +x /usr/share/tomcat7/bin/setenv.sh
service tomcat7 start

tail -f /var/log/tomcat7/catalina.out
