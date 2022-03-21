#!/bin/bash

./gradlew build

docker build -t jyunkim/bookmarkit-api:dev --build-arg APP_PROFILE=dev .
cat ./secret.txt | docker login -u jyunkim --password-stdin
docker push jyunkim/bookmarkit-api:dev
