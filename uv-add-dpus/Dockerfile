FROM debian:jessie

MAINTAINER Niels Vandekeybus <niels.vandekeybus@tenforce.com>
ADD dpus /dpus
VOLUME /dpus
ENV MASTER_USER=master MASTER_PASSWORD=commander
RUN apt-get update && apt-get install -y curl
ADD startup.sh add-dpu.sh /usr/local/bin/
CMD ["/bin/bash", "/usr/local/bin/startup.sh"]

