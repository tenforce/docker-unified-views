FROM java:8-jre-alpine

MAINTAINER Erika Pauwels <erika.pauwels@tenforce.com>
MAINTAINER Bert Van Nuffelen <bert.van.nuffelen@tenforce.com>

RUN apk add --no-cache bash 

ENV UNIFIED_VIEWS_VERSION 2.3.1
RUN mkdir -p /packages /config /unified-views/backend/working /unified-views/dpu /unified-views/lib /unified-views/logs && touch /unified-views/logs/backend.log
ADD backend-$UNIFIED_VIEWS_VERSION.jar /unified-views/backend.jar
ADD startup.sh /bin
EXPOSE 5001
WORKDIR /unified-views
ENTRYPOINT ["/bin/bash"]
CMD ["/bin/startup.sh"]
