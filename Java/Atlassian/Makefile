# Makefile for the Atlassian module
# Author: Rohtash Lakra

MODULE        := Atlassian
REVISION      ?= 0.0.0
# InterviewMap or com.rslakra.interview.atlassian.InterviewPriorityQueue
MAIN_CLASS    ?= com.rslakra.interview.atlassian.InterviewMap
# runs against the parent pom, building this module and Common (-am), so nothing has to be installed first
MVN           := mvn -B -f ../pom.xml -Drevision=$(REVISION) -pl $(MODULE) -am

.DEFAULT_GOAL := help

.PHONY: help clean build test run

help:
	@echo "Usage: make <target>"
	@echo ""
	@echo "  clean                Remove build output"
	@echo "  build                Build the module without tests (REVISION=$(REVISION))"
	@echo "  test                 Run unit tests"
	@echo "  run                  Run $(MAIN_CLASS)"
	@echo "                       make run MAIN_CLASS=com.rslakra.interview.atlassian.InterviewPriorityQueue"

clean:
	$(MVN) clean

build:
	$(MVN) package -DskipTests=true

test:
	$(MVN) test

run:
	$(MVN) -q compile exec:java -Dexec.mainClass=$(MAIN_CLASS)
