FROM eclipse-temurin:17-jdk-jammy as build
LABEL authors="Stephen Scarlett"

WORKDIR /workspace/app

#RUN pwd
RUN mkdir -p target/dependency
COPY target/*.jar target/app.jar
#RUN (cd target; ls -la)
RUN (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jdk-jammy
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-cp","app:app/lib/*","com.scarlett.big_ambitions_companion.BigAmbitionsCompanionApplication"]
