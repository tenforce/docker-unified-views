FROM java:8-jre-alpine

MAINTAINER Niels Vandekeybus <niels.vandekeybus@tenforce.com>
MAINTAINER Bert Van Nuffelen <bert.van.nuffelen@tenforce.com>

RUN apk add --no-cache bash gawk sed grep bc coreutils

ENV UNIFIED_VIEWS_VERSION 2.3.1

RUN mkdir -p /unified-views
ADD packages/lib /unified-views/lib
ADD config /config

VOLUME /unified-views

RUN chmod +x /config/start.sh && chmod -R 777 /config

ENTRYPOINT ["/config/start.sh"]
