FROM gradle:8.14.3-jdk21-alpine AS builder

WORKDIR /ms-user

COPY . .

RUN gradle dependencies
RUN gradle clean bootJar

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

COPY --from=builder /ms-user/build/libs/*.jar app.jar

RUN chown appuser:appgroup /app/app.jar
USER appuser

EXPOSE 8080

#ENV JAVA_OPTS=" -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java -jar app.jar" ]