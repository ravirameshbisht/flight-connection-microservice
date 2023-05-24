# Build stage
FROM eclipse-temurin:11-jdk-jammy as builder
WORKDIR /opt/app
COPY . .
RUN ./gradlew clean build --no-daemon --console=plain

# Production stage
FROM eclipse-temurin:11-jre-jammy
WORKDIR /opt/app
COPY --from=builder /opt/app/build/libs/*.jar gateway-1.0.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","gateway-1.0.jar"]