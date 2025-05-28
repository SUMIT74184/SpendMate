FROM openjdk:21
WORKDIR /usr/src/myapp
COPY build/libs/userservice-0.0.1-SNAPSHOT.jar /app/userservice-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","example_jar"]

EXPOSE 9590