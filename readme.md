## Test task

### Prerequisites

- make sure you have Java 11 installed
- make sure you have node.js 10+ installed
- make sure you have Angular CLI installed



### How to start

- run app with prod profile
```
 ./gradlew bootRun
```

- run app with dev profile
```
./gradlew bootRun --args='--spring.profiles.active=dev'
```

To change number of returned books/albums - 
go into application-***.properties of your selected profile and change
`api.result.maxsize` value