FROM openjdk:8-jdk-alpine

ENV PROFILE_ACTIVE dev

WORKDIR /app

# Add Maintainer Info
LABEL maintainer="truyenminhtoan@gmail.com"

# Make port 9500 available to the world outside this container
EXPOSE 9500

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container


ADD ${JAR_FILE} /app/app.jar


RUN apk --update add tzdata && \
    cp /usr/share/zoneinfo/Asia/Ho_Chi_Minh /etc/localtime && \
    apk del tzdata && \
    rm -rf /var/cache/apk/*


ENTRYPOINT ["sh", "-c" , "exec java -Dspring.profiles.active=${PROFILE_ACTIVE} -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar" ]

