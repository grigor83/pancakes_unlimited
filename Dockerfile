FROM openjdk:11

ADD target/pancakes_unlimited-1.jar pancakes_unlimited_docker.jar

ENTRYPOINT ["java", "-jar","pancakes_unlimited_docker.jar"]

EXPOSE 8080