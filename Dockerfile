FROM maven:3.6.3-openjdk-14 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
COPY ./src/main/resources/app.properties /build/src/main/resources/application.properties
WORKDIR /build/
RUN mvn package -Dmaven.test.skip=true
FROM openjdk:14-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/aplmu.jar /app/
ENV restpath="/api" dburl="" dbun="" dbpd="" ossak="" osssk="" ossendpoint="" ossbucket=""
EXPOSE 80
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar", "aplmu.jar","--spring.data.rest.base-path=${restpath}","--spring.datasource.url=${dburl}","--spring.datasource.username=${dbun}","--spring.datasource.password=${dbpd}","--alioss.accessId=${ossak}","--alioss.accessKey=${osssk}","--alioss.endpoint=${ossendpoint}","--alioss.bucket=${ossbucket}"]