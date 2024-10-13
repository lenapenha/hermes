.PHONY: run
run:
	make build
	docker-compose up --build -d

.PHONY: build
build:
	./gradlew build