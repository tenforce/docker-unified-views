FROM ubuntu:14.04.3

MAINTAINER Erika Pauwels <erika.pauwels@tenforce.com>

ENV UV_DATABASE_SQL_URL=jdbc:mysql://mysql:3306/unified_views_?characterEncoding=utf8  UV_DATABASE_SQL_USER=unified_views UV_DATABASE_SQL_PASSWORD=unified_views MASTER_USER=master MASTER_PASSWORD=commander
ENV DOWNLOAD_DPUS=true
ENV UV_VERSION=2.2.0
ENV PLUGINS_VERSION_TAG=UV_Plugins_v$UV_VERSION
ENV CORE_VERSION_TAG=UV_Core_v$UV_VERSION

RUN apt-get update \
      && apt-get install -y openjdk-7-jdk maven tomcat7 curl git mysql-client \
      && mkdir /packages \
      && mkdir /config \
      && mkdir /dpus \
      && mkdir /logs && touch /logs/frontend.log && touch /logs/frontend_err.log \
      && mkdir -p /unified-views/dpu /unified-views/lib \
      && sed -i "s/^TOMCAT7_USER.*/TOMCAT7_USER=root/" /etc/default/tomcat7 \
      && sed -i "s/^TOMCAT7_GROUP.*/TOMCAT7_GROUP=root/" /etc/default/tomcat7 \
      && curl --output /unified-views/lib/pom.xml --silent http://maven.eea.sk/artifactory/public/eu/unifiedviews/lib-core/${UV_VERSION}/lib-core-${UV_VERSION}.pom \
      && cd /unified-views/lib \
      && sed -n -i '1h;1!H;${g;s/<parent>.*<\/parent>//;p;}' pom.xml \
      && sed -i "s#<artifactId>lib-core</artifactId>#<artifactId>lib-core</artifactId><version>$UV_VERSION</version><groupId>eu.unifiedviews</groupId>#" pom.xml \
      && sed -i 's#${project.output.lib}#/unified-views/lib#' /unified-views/lib/pom.xml \
      && mvn dependency:copy-dependencies -DoutputDirectory=/unified-views/lib

ADD tomcat-setenv.sh /usr/share/tomcat7/bin/setenv.sh
ADD http://maven.eea.sk/artifactory/public/eu/unifiedviews/frontend/$UV_VERSION/frontend-$UV_VERSION.war /packages/frontend.war
ADD http://maven.eea.sk/artifactory/public/eu/unifiedviews/backend/$UV_VERSION/backend-$UV_VERSION.jar /packages/backend.jar
ADD http://maven.eea.sk/artifactory/public/eu/unifiedviews/master/$UV_VERSION/master-$UV_VERSION.war /packages/master.war
ADD config /config
ADD startup.sh /
ADD add-dpu.sh /
ADD env-to-java-properties-file.sh /

WORKDIR /unified-views

EXPOSE 8080
EXPOSE 5001

CMD ["/bin/bash", "/startup.sh"]