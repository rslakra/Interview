# Makefile for the Walmart module
# Author: Rohtash Lakra

MODULE        := Walmart
REVISION      ?= 0.0.0
# runs against the parent pom, building this module and Common (-am), so nothing has to be installed first
MVN           := mvn -B -f ../pom.xml -Drevision=$(REVISION) -pl $(MODULE) -am

.DEFAULT_GOAL := help

.PHONY: help clean build test

help:
	@echo "Usage: make <target>"
	@echo ""
	@echo "  clean                Remove build output"
	@echo "  build                Build the module without tests (REVISION=$(REVISION))"
	@echo "  test                 Run unit tests (this module has no main class)"

clean:
	$(MVN) clean

build:
	$(MVN) package -DskipTests=true

test:
	$(MVN) test
