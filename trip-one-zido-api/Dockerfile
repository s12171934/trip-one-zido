FROM amazoncorretto:17.0.10
COPY build/libs/*.jar docker-springboot.jar
ENTRYPOINT ["java","-jar", "docker-springboot.jar"]