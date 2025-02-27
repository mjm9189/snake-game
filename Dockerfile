FROM ubuntu:latest
LABEL authors="michael"

ENV DEBIAN_FRONTEND=noninteractive

# Install OpenJDK 23.0.2
RUN apt update -y && \
    apt upgrade -y && \
    apt install -y openjdk-21-jdk
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-arm64
ENV PATH=$PATH:$JAVA_HOME/bin

# Set user permissions
RUN addgroup --system spring && \
    adduser --system spring --ingroup spring
USER spring:spring

# Optimize performance
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","c.s.snakegame.SnakegameApplication"]