FROM maven:3.5.4

RUN mkdir -p /opt/votesapp
COPY . /opt/votesapp
WORKDIR /opt/votesapp
RUN if [ ! -f "src/main/resources/application.properties" ]; then \
      cp src/main/resources/applications.properties.sample src/main/resources/applications.properties && \
      sed -i 's/localhost:3306/db:3306/' src/main/resources/applications.properties; \
    fi
CMD mvn spring-boot:run
