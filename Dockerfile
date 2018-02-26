FROM alpine/git
ARG url
WORKDIR /app
RUN git clone ${url}

FROM maven:3.5-jdk-8-alpine
ARG project
WORKDIR /app
COPY --from=0 /app/${project} /app
RUN mvn install

FROM openjdk:8-jre-alpine
ARG artifactid
ARG version
ARG port
ENV artifact ${artifactid}-${version}.jar
WORKDIR /app
COPY --from=1 /app/target/${artifact} /app
EXPOSE ${port}
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar -Dapp.port=${port} ${artifact}"]