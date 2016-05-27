FROM alpine:3.3

MAINTAINER Niels Vandekeybus <niels.vandekeybus@tenforce.com>
ADD dpus /dpus
VOLUME /dpus
ENV MASTER_USER=master MASTER_PASSWORD=commander

RUN apk add --no-cache bash curl
RUN mkdir -p /var/log/adddpus
RUN chmod 777 /var/log/adddpus
volume /var/log/adddpus
ADD startup.sh add-dpu.sh /usr/local/bin/
CMD ["/bin/bash", "/usr/local/bin/startup.sh"]

