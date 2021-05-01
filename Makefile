APP_NAME=hikingtrail-service
MONGO_NAME=mongodb
MONGO_SEED=mongo-seed

build-gradle:
	./gradlew clean build

build-app:
	docker compose build --no-cache $(APP_NAME)

build-mongo:
	docker compose build --no-cache $(MONGO_NAME)

build-mongo-seed:
	docker compose build --no-cache $(MONGO_SEED)

run:
	docker compose up

test:
	./gradlew test

stop:
	docker compose down

refresh: build-gradle stop build-app build-mongo run
