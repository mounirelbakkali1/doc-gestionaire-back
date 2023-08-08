FROM maven:3.8.4-openjdk-17 AS build


WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ ./src/

RUN mvn clean package


FROM openjdk:17
COPY --from=build /app/target/Myapp.jar .


CMD ["java","-jar","Myapp.jar"]

RUN rm -rf target

# if (running build localy) ------------------- 


# FROM openjdk:17

# WORKDIR /app

# ADD target/Myapp.jar /app/

# CMD ["java","-jar","Myapp.jar"]

#--------------------------------------------------
