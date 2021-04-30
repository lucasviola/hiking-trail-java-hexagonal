APP_NAME=hikingTrail

build-gradle:
	./gradlew clean build

build-app:
	docker compose build --no-cache $(APP_NAME)

run:
	docker compose up -d

test:
	./gradlew test

stop:
	docker compose down

refresh: build-gradle stop build-app run
