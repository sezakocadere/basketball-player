# basketball-player

## About The Project

A **Spring Boot** application that utilizes **GraphQL.** Used **JPA** to interact with **H2 database**. This project using **Lombok** and **Java 17**.

## API DOCS

API Query Type | API Query| Description |
|--|--|--|
| Query	| getAllPlayers(page: Int!, size: Int!)|Paginated list of all players| 
| Mutation | createPlayer(player: PlayerRequest!) |Create a new player in the system|
| Mutation | removePlayer(id: String!)	|Change status to passive of player from the system|
| Mutation|createTeam(team: TeamRequest!)	|Create a new team in the system|
| Mutation|createUser(user :UserRequest!)	|Create a user in the system|
| Query |login(userRequest: UserRequest!) |Authenticate a user and generate a token|
| Query	|getHistory() |List of all system transactions|

## Docker Images
Run ```docker build --tag=basketball:v1.1 .```  to build the application.

If you have already generated and reload it.

``` docker run -p8080:8080 basketball:v1.1    ``` 

The project has been Dockerized and pushed to Docker Hub. You can access it through the following link: https://hub.docker.com/r/sezakocadere/basketball/tags

## Request Examples

### Create User
To obtain a token, we first need to create a user can executed by Anonymous users.
```
localhost:8080/graphql
Request Body

mutation {
  createUser(user: {username: "seza", password:"123"}) {
   id
  }
}

Response Body

{
    "data": {
        "createUser": {
            "id": "1"
        }
    }
}
```

### Login User (Authentication Step)
This login query returns a bearer token and can executed by Anonymous users.
```
localhost:8080/graphql

Request Body

query {
  login(userRequest: {
    username: "seza",
    password: "password"
  })
}

Response Body
{
    "data": {
        "login": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZXphIiwiZXhwIjoxNjc5ODg3Mjc3LCJpYXQiOjE2Nzk4NjkyNzd9.K7LMS30p56vjZSWWnUPtfIbm3eSikiBQD12d8ngT2wE"
    }
}
```


### Create Team
it can only be executed by authenticated users (is not Anonymous) 
```
localhost:8080/graphql
Request Body

mutation {
  createTeam(team: {name: "team1"}) {
   id
  }
}


Response Body

{
    "data": {
        "createTeam": {
            "id": "2"
        }
    }
}
```

### Create Player
it can only be executed by authenticated users (is not Anonymous) 
```
localhost:8080/graphql
Request Body

mutation {
  createPlayer(player: {name: "seza", surname:"kocadere", position:PF, teamId:2}){
    id
    name
    surname
  }
}

Response Body

{
    "data": {
        "createPlayer": {
            "id": "12",
            "name": "seza",
            "surname": "kocadere"
        }
    }
}
```

## H2 Memory Database
http://localhost:8080/h2-console/
```
username: sa

password: 1234567
```
