# Makefile for Interview (parent of the interview modules)
# Author: Rohtash Lakra

REVISION      ?= 0.0.0
MODULES       := Common Atlassian Finfare Walmart Fico
# modules with a main class
RUNNABLE      := Atlassian Finfare
MVN           := mvn -B -Drevision=$(REVISION)

.DEFAULT_GOAL := help

.PHONY: help clean build test $(MODULES:%=build-%) $(MODULES:%=test-%) $(RUNNABLE:%=run-%)

help:
	@echo "Usage: make <target>"
	@echo ""
	@echo "All modules ($(MODULES)):"
	@echo "  clean                Remove build output of every module"
	@echo "  build                Build every module without tests (REVISION=$(REVISION))"
	@echo "  test                 Run the tests of every module"
	@echo ""
	@echo "One module (same as running make inside the module folder):"
	@for m in $(MODULES); do echo "  build-$$m, test-$$m"; done
	@for m in $(RUNNABLE); do echo "  run-$$m"; done
	@echo ""
	@echo "Common is built together with the module that needs it, so nothing has to be installed first."

clean:
	$(MVN) clean

build:
	$(MVN) package -DskipTests=true

test:
	$(MVN) test

$(MODULES:%=build-%): build-%:
	$(MAKE) -C $* build REVISION=$(REVISION)

$(MODULES:%=test-%): test-%:
	$(MAKE) -C $* test REVISION=$(REVISION)

$(RUNNABLE:%=run-%): run-%:
	$(MAKE) -C $* run REVISION=$(REVISION)
