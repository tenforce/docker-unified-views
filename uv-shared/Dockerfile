FROM andreptb/oracle-java:8

MAINTAINER Niels Vandekeybus <niels.vandekeybus@tenforce.com>

ENV UNIFIED_VIEWS_VERSION 2.3.0
ADD config /config
ADD packages/lib /unified-views/lib
ADD env-to-java-properties-file.sh /usr/local/bin/
RUN apk add --update bash && rm -rf /var/cache/apk/* 
VOLUME /config
VOLUME /unified-views/lib
VOLUME /unified-views/dpu
CMD ["/bin/bash","/usr/local/bin/env-to-java-properties-file.sh"]
