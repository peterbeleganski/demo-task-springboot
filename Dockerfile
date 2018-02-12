FROM java:8
ADD target/csv-mongo.jar csv-mongo.jar
EXPOSE 8080
RUN bash -c touch 'csv-mongo.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/test", "-Djava.security.egd=file:/dev/./urandom","-jar","/csv-mongo.jar"]

