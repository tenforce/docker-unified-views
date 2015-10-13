FROM ubuntu:14.04

MAINTAINER Erika Pauwels <erika.pauwels@tenforce.com>

ENV UV_DATABASE_SQL_URL=jdbc:mysql://mysql:3306/unified_views_?characterEncoding=utf8  UV_DATABASE_SQL_USER=unified_views UV_DATABASE_SQL_PASSWORD=unified_views MASTER_USER=master MASTER_PASSWORD=commander
ENV DOWNLOAD_DPUS=true
ENV PLUGINS_VERSION_TAG=UV_Plugins_v2.2.0
RUN apt-get update \
      && apt-get install -y openjdk-7-jdk maven tomcat7 curl git mysql-client \
      && mkdir /packages \
      && mkdir /config \
      && mkdir /dpus \
      && mkdir /logs && touch /logs/frontend.log && touch /logs/frontend_err.log \
      && mkdir -p /unified-views/dpu \
      && sed -i "s/^TOMCAT7_USER.*/TOMCAT7_USER=root/" /etc/default/tomcat7 \
      && sed -i "s/^TOMCAT7_GROUP.*/TOMCAT7_GROUP=root/" /etc/default/tomcat7

ADD tomcat-setenv.sh /usr/share/tomcat7/bin/setenv.sh
ADD packages /packages
ADD config /config
ADD startup.sh /
ADD add-dpu.sh /
ADD env-to-java-properties-file.sh /

WORKDIR /unified-views

EXPOSE 8080
EXPOSE 5001

CMD ["/bin/bash", "/startup.sh"]