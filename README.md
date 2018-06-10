# MapNotes-Backend

Map Notes is app, which helps organize notes.
Therefore one can create notes on map, give them attribute and filter.

Project was realized as assignment from AGH course "Software Engineering"

## Table of Contents

- [Demo](#demo)
- [Frontend](#frontend)
- [Technologies](#technologies)
- [API](#api)
- [Deploy](#deploy)
- [Configuration](#configuration)
- [Contribution](#contribution)
  - [CI](#ci)
  - [Review](#review)
  - [Automatic Deploy](#automatic-deploy)
  
## Demo

Current version of project can be tested [here](http://map-notes-backend.herokuapp.com/)

## Frontend

Information about frontend part of project can be found [here](https://github.com/Kotwic4/MapNotes-Frontend)

## Technologies

Project was realised in Java with Spring framework.

## API

Javadoc with information about API is [here](https://kotwic4.github.io/MapNotes-Backend/)

## Deploy

In [releases](https://github.com/Kotwic4/MapNotes-Backend/releases) there is complied jar file,
which can start server like:

```
java -jar MapNotes-Backend-1.0.0.jar
```

The project is open source, therefor it is easy to build jar from source with gradle.

## Configuration

In default configuration app run on port 8080 and have database in memory.
The example below change port to 5000 and use postgresql database.

The easiest way is by environment variables:
```
set SERWER_PORT=5000
set SPRING_DATASOURCE_URL=jdbc:postgresql://example.com:5432/user_database
set SPRING_DATASOURCE_USERNAME=user
set SPRING_DATASOURCE_PASSWORD=secret_password
set SPRING_JPA_GENERATE-DDL=true
set SPRING_JPA_HIBERNATE.DDL-AUTO=create
```

One can also chance configuration by application.properties:
``` 
server.port=5000
spring.datasource.url=jdbc:postgresql://example.com:5432/user_database
spring.datasource.username=user
spring.datasource.password=secret_password
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create
```

## Contribution

Project is relised as open source, so if you find any bug, you can fixed it.

If you don't know how to fix or don't have time for it leave an issue.

### CI

Every commmit pushed to github is verified by Travis.
Pull Request must pass check before merging to master.

### Review

Every Pull Request must have approved review from another user.

### Automatic deploy

Master branch is automaticly deployed to heroku servers after CI check.
