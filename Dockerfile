FROM openjdk:11.0.7-slim
LABEL maintainer="me@mail.com"
ARG JAR_FILE
ADD target/${JAR_FILE} ms-producto.jar
RUN echo ${JAR_FILE}
ENTRYPOINT ["java","-jar","/ms-producto.jar"]