FROM openjdk:11-jre

ARG APP_PROFILE=local
ENV APP_PROFILE=${APP_PROFILE} \
    TZ=Asia/Seoul

WORKDIR /app
COPY build/libs/* /app/

ENTRYPOINT java \
  -jar /app/bookmarkit-api.jar \
  --spring.profiles.active=${APP_PROFILE}
