FROM maven:3.5.4 as dependencies

RUN mkdir -p /opt/votesapp
WORKDIR /opt/votesapp
COPY ./pom.xml .
RUN mvn dependency:resolve dependency:resolve-plugins


FROM maven:3.5.4 as build

RUN mkdir -p /root/.m2
COPY --from=dependencies /root/.m2 /root/.m2
RUN mkdir -p /opt/votesapp
WORKDIR /opt/votesapp
COPY . .
RUN if [ ! -f "src/main/resources/application.properties" ]; then \
      cp src/main/resources/application.properties.sample src/main/resources/application.properties && \
      sed -i 's/localhost:3306/db:3306/' src/main/resources/application.properties; \
    fi
RUN mvn clean package -DskipTests


FROM openjdk:8-jre

RUN mkdir -p /opt/votesapp
COPY --from=build /opt/votesapp/target/*.jar /opt/votesapp/
CMD java -jar /opt/votesapp/*.jar
