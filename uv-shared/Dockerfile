FROM java:8-jre-alpine

MAINTAINER Niels Vandekeybus <niels.vandekeybus@tenforce.com>
MAINTAINER Bert Van Nuffelen <bert.van.nuffelen@tenforce.com>

RUN apk add --no-cache bash gawk sed grep bc coreutils

ENV UNIFIED_VIEWS_VERSION 2.3.0
ADD config /config
ADD packages/lib /unified-views/lib
ADD env-to-java-properties-file.sh /usr/local/bin/

VOLUME /unified-views/lib
VOLUME /unified-views/dpu
VOLUME /unified-views/logs
RUN chmod 777 /unified-views/logs
RUN chmod 777 /unified-views/dpu

ENTRYPOINT ["/bin/bash"]
RUN chmod +x /usr/local/bin/env-to-java-properties-file.sh 
RUN /usr/local/bin/env-to-java-properties-file.sh
VOLUME /config
