FROM openjdk:11

COPY . /usr/app
WORKDIR /usr/app

RUN ./gradlew build

COPY ./build/libs/ganho-de-capital*.jar ./app.jar