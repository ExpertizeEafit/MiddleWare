FROM gradle:7-jdk17 as build
WORKDIR /middleware
COPY . .
RUN ./gradlew build --stacktrace 

FROM openjdk:17
WORKDIR /middleware
EXPOSE 80
COPY --from=build /middleware/build/libs/middleware-0.0.1-SNAPSHOT.jar .
CMD java -jar middleware-0.0.1-SNAPSHOT.jar