DOCKER_FILE = ./docker-compose.yml
DOCKER_COMPOSE_BIN = docker-compose -f $(DOCKER_FILE)

.PHONY: start server
start:
	$(DOCKER_COMPOSE_BIN) up nginx -d
	$(DOCKER_COMPOSE_BIN) exec nginx bash

.PHONY: stop server
stop:
	$(DOCKER_COMPOSE_BIN) down nginx
