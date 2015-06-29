FROM ubuntu:14.04

MAINTAINER Erika Pauwels <erika.pauwels@tenforce.com>

RUN apt-get update \
      && apt-get install -y openjdk-7-jdk maven tomcat7 git mysql-client \
      && mkdir /packages \
      && mkdir /config \
      && mkdir /logs && touch /logs/frontend.log && touch /logs/frontend_err.log \
      && mkdir /unified-views

ADD tomcat-setenv.sh /usr/share/tomcat7/bin/setenv.sh
ADD packages /packages
ADD config /config
ADD startup.sh /

WORKDIR /unified-views

EXPOSE 8080
EXPOSE 5001

CMD ["/bin/bash", "/startup.sh"]