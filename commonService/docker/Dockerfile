FROM openjdk:17-alpine

ENV KAFKA_VERSION=3.6.0
ENV SCALA_VERSION=2.13
ENV KAFKA_HOME=/opt/kafka
ENV PATH=${PATH}:${KAFKA_HOME}/bin

LABEL name="kafka" version=${KAFKA_VERSION}
#RUN apt install -y wget
RUN apk update && apk add wget
RUN wget -O /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
 && tar xfz /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -C /opt \
 && rm /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
 && ln -s /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION} ${KAFKA_HOME} \
 && rm -rf /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz

COPY ./entrypoint.sh /
RUN ["chmod", "+x", "/entrypoint.sh"]
ENTRYPOINT ["/entrypoint.sh"]


#-------------------------------
#FROM openjdk:17-alpine
#
#ENV KAFKA_VERSION=2.8.1
#ENV SCALA_VERSION=2.13
#ENV KAFKA_HOME=/opt/kafka
#ENV PATH=${PATH}:${KAFKA_HOME}/bin
#
#LABEL name="kafka" version=${KAFKA_VERSION}
#
## Use a different Alpine Linux mirror (you can replace mirror.example.com with a reliable mirror)
#RUN sed -i -e 's/dl-cdn.alpinelinux.org/mirror.example.com/g' /etc/apk/repositories
#
## Install wget using apk
#RUN apk update && apk add --no-cache wget
#
## Download and set up Kafka
#RUN wget -O /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
# && tar xfz /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -C /opt \
# && rm /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
# && ln -s /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION} ${KAFKA_HOME}
#
#COPY ./entrypoint.sh /
#RUN ["chmod", "+x", "/entrypoint.sh"]
#ENTRYPOINT ["/entrypoint.sh"]

#-------------------------------
#FROM openjdk:17
#
#ENV KAFKA_VERSION=2.8.1
#ENV SCALA_VERSION=2.13
#ENV KAFKA_HOME=/opt/kafka
#ENV PATH=${PATH}:${KAFKA_HOME}/bin
#
#LABEL name="kafka" version=${KAFKA_VERSION}
#
## Install wget and unzip using apt
#RUN apt-get install -y wget
#
## Download and set up Kafka
#RUN wget -O /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
# && tar xfz /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -C /opt \
# && rm /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
# && ln -s /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION} ${KAFKA_HOME}
#
#COPY ./entrypoint.sh /
#RUN ["chmod", "+x", "/entrypoint.sh"]
#ENTRYPOINT ["/entrypoint.sh"]
