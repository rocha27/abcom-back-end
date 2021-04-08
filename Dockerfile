# Teste local, usando Alpine (imagem menor)
# Alterar BASE_IMAGE e RUN para scripts do Alpine
# "docker build --build-arg JAR_NAME="app.jar" -t docker.agricultura.gov.br/abcom_api-mapa:latest ."
#
# Para executar "docker-compose up"
# Diponivel em localhost:8051/abcom_api e localhost:8052/abcom_api (Ver docker-compose.yml)
#

FROM docker.agricultura.gov.br/jre-mapa:8
# ALPINE
#FROM openjdk:8-jre-alpine


ARG JAR_NAME

LABEL maintainer="cgti.docker@agricultura.gov.br"

ENV APP_DIR /u01/app
ENV LOG_DIR /u01/log
ENV APP_NAME app.jar
ENV USER userAPP
ENV GROUP groupAPP

RUN mkdir -p $APP_DIR && \
    mkdir -p $LOG_DIR && \
# java-mapa
    groupadd -g 1001 $GROUP && \
    useradd -g 1001 $USER
# ALPINE
#    addgroup -S $GROUP && \
#    adduser -S $USER -G $GROUP


WORKDIR $APP_DIR

ADD target/$JAR_NAME $APP_DIR

RUN echo $JAR_NAME

RUN ls -la

RUN chown -R $USER:$GROUP $APP_DIR && \
    chown -R $USER:$GROUP $LOG_DIR

USER $USER


CMD java -jar $APP_NAME
