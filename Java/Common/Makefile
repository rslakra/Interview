# Makefile for the Common module
# Author: Rohtash Lakra

MODULE        := Common
REVISION      ?= 0.0.0
# runs against the parent pom, building only this module (and the modules it depends on)
MVN           := mvn -B -f ../pom.xml -Drevision=$(REVISION) -pl $(MODULE) -am

.DEFAULT_GOAL := help

.PHONY: help clean build test

help:
	@echo "Usage: make <target>"
	@echo ""
	@echo "  clean                Remove build output"
	@echo "  build                Build the module without tests (REVISION=$(REVISION))"
	@echo "  test                 Run unit tests"

clean:
	$(MVN) clean

build:
	$(MVN) package -DskipTests=true

test:
	$(MVN) test
