# Interview

---

Each interview is a separate Maven module that can be built and run on its own. Code that isn't specific to one
interview lives in `Common`.

| Module      | Contents                                                                                            |
|-------------|-----------------------------------------------------------------------------------------------------|
| `Common`    | Shared code: CSV reader/writer, `Utils`, shapes (`common`) and the default `logback.xml`              |
| `Atlassian` | Most popular content (`InterviewMap`, `InterviewPriorityQueue`, `Interview`, `Pair`)                |
| `Finfare`   | Transaction log processing (`Finfare`)                                                              |
| `Walmart`   | Max distance between flowers (`FindMaxDistanceBetweenFlowers`)                                      |
| `Fico`      | Login data-provider test and DB procedure notes (test sources only)                                 |

Atlassian, Finfare, Walmart and Fico depend on `Common`.

# Usage

Run these from this directory. `-am` also builds `Common`, so nothing has to be installed first.

```shell
# build and test everything
mvn clean verify

# build and test one module
mvn -pl Walmart -am test

# run a module's main class
mvn -q -pl Finfare -am compile exec:java
mvn -q -pl Atlassian -am compile exec:java
mvn -q -pl Atlassian -am compile exec:java -Dexec.mainClass=com.rslakra.interview.atlassian.InterviewPriorityQueue
```

`Walmart` and `Fico` have no main class, so run them through their tests.

## Makefiles

The parent and every module have a Makefile (`make help` lists the targets). `REVISION` defaults to `0.0.0`.

```shell
# from this directory
make build                  # build every module without tests
make test                   # test every module
make test-Walmart           # one module, delegates to Walmart/Makefile
make run-Finfare

# from inside a module
cd Atlassian
make test
make run
make run MAIN_CLASS=com.rslakra.interview.atlassian.InterviewPriorityQueue
```


# Build

[![Java Build with Maven](https://github.com/rslakra/Interview/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/rslakra/Interview/actions/workflows/maven-publish.yml)

# Reference(s)



# Author

- Rohtash Lakra
