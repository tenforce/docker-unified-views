FROM tomcat:7-jre8

MAINTAINER Erika Pauwels <erika.pauwels@tenforce.com>
MAINTAINER Niels Vandekeybus <niels.vandekeybus@tenforce.com>

ENV UNIFIED_VIEWS_VERSION 2.3.0
ADD tomcat-setenv.sh /usr/local/tomcat/bin/setenv.sh
ADD unifiedviews-$UNIFIED_VIEWS_VERSION.war /usr/local/tomcat/webapps/unifiedviews.war
ADD master-$UNIFIED_VIEWS_VERSION.war /usr/local/tomcat/webapps/master.war
RUN chmod +x /usr/local/tomcat/bin/setenv.sh