
FROM java:8
MAINTAINER Maksim Kulikov
ADD /target/home-bot.jar home-bot.jar
CMD ["java","-jar","home-bot.jar"]


