FROM openjdk:8
COPY ./target/read-marketing-preference-0.0.1-SNAPSHOT.jar  read-marketing-preference-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","read-marketing-preference-0.0.1-SNAPSHOT.jar"]