FROM maven:3.8.4-openjdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install -Denv=test -DskipTests package


FROM openjdk:8
COPY --from=build /home/app/target/dpomanager.jar /usr/local/lib/alessio-prj-be.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/usr/local/lib/alessio-prj-be.jar"]
