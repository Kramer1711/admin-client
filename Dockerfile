FROM frolvlad/alpine-oraclejdk8:slim

COPY target/app1-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

VOLUME ["/data","/data"]