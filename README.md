# TermTrain

TermTrain is a web application designed and written for my BA thesis. Now I want to develop this, add some tests, security and deploy it on Heroku.
The frontend for this backend is the TermTrain-client repository

<h1>General info</h1>
The main goal of the work is to create a didactic application that imitates the behavior of the command line. This app will help new users learn how to use Linux terminal commands.

<h2>TODO's:</h2>
<ul>
  <li>
    A web application that allows you to use the bash terminal in a web browser without having to install Linux or control it on a virtual machine.
  </li>
  <li>
  The web application allows you to learn bash terminal commands through hands-on exercises.
  </li>
  <li>
   Aplikacja zawiera podstawowe polecenia terminala systemu Linux, takie jak: ls, cd, cp, mv, cat, rm, mkdir, rmdir, touch, nano, man. 
  </li>
  <li>
   Users will be able to consolidate their knowledge by taking a quiz.
    </li>
      <li>
  Before starting the quiz, the user will be able to read this short description of the commands.
      </li>
  <li>
While taking the quiz, users will be able to test their own
answer by typing the appropriate commands in the terminal.
  </li>
  <li>
An additional functionality of the application will be the possibility for users to leave questions related to the Linux system or the bash terminal. Users will
they could also leave their comments under selected questions.
  </li>
</ul>

<h2>Technologies</h2>

<h4>Backend</h4>
  
```
  * Java
  * Spring Boot
  * JPA
  * Hibernate
  * Flayway
  * Lombok
  * Swagger
  * Mockito
  * JUnit
  * Assertj
```
  
  <h4>Frontend</h4>
  
```
  * JavaScript
  * React
  * MUI
  * Redux
```
  
  <h4>Database</h4>
  
```
  * H2
```

<h4>CI/CD</h4>

```
  * Heroku
  * Circleci
  * Postgres
```

==== Endpoints:
* /api/v1/login
    - `POST` - unauthorized login endpoint for users
* /api/v1/register 
   - `POST` - unauthorized registration endpoint for users
* /api/v1/name/{name}
    - `GET` - an authorized endpoint for retrieving a list of files by name
* /api/v1/path/{path}
    - `GET` - an authorized endpoint for retrieving a list of files by path
* /api/v1/path-name/{file}
    - `GET` - an authorized endpoint for retrieving a files by path and name
* /api/v1/add-new-file
    - `POST` - an authorized endpoint for creating new file
* /api/v1/update-file/{id}
    - `PUT` - an authorized endpoint for udating file
  
==== Example requests:
```json
   POST localhost:8080/api/v1/login
        Content-Type: application/json
{
    "username": "user@domain.com",
    "password":"SomePassword1"
}
```

```json
{
    "token": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InVzZXJAZG9tYWluLmNvbSIsIl9pZCI6IjYyMTBiMWEzLTI0OTktNDQ2ZC1hNjg3LWNjZTAxMGE0OTg2NCIsImlhdCI6MTY3NTA3MjE5NywiZXhwIjoxNjc1MDc1Nzk3fQ.5EF_ZK6zin9dDUCCN6FCAmGkffwrLdfAdIaRuNleBas"
}
```

```json
  POST localhost:8080/api/v1/register
        Content-Type: application/json
{
    "name": "user",
    "username": "user@domain.com",
    "password":"SomePassword1"
}
```

```json
{
   "c34f3c1c-b828-40fc-9c80-206f49ae2388"
}
```


```json
  GET localhost:8080/api/v1/name/home
        Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InVzZXJAZG9tYWluLmNvbSIsIl9pZCI6IjYyMTBiMWEzLTI0OTktNDQ2ZC1hNjg3LWNjZTAxMGE0OTg2NCIsImlhdCI6MTY3NTA3MTAwOSwiZXhwIjoxNjc1MDc0NjA5fQ.Sri_STBZQM5h_PuEZGQs3cbvFABTWf4_5qaw-94iJPk
```

```json
[
     {
          "id": 3,
          "name": "home",
          "path": "/",
          "permisions": "drwxr-xr-x",
          "link": 3,
          "username": "root",
          "groupname": "root",
          "size": 4096,
          "time": "Jul 31 22:29",
          "isDirectory": true,
          "text": ""
     }
]
```

`Attention!`

```
(instead of using "/" in the path we have to use "-")
```

```json
  GET localhost:8080/api/v1/path/-
        Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InVzZXJAZG9tYWluLmNvbSIsIl9pZCI6IjYyMTBiMWEzLTI0OTktNDQ2ZC1hNjg3LWNjZTAxMGE0OTg2NCIsImlhdCI6MTY3NTA3MTAwOSwiZXhwIjoxNjc1MDc0NjA5fQ.Sri_STBZQM5h_PuEZGQs3cbvFABTWf4_5qaw-94iJPk
```

```json
[
     {
          "id": 3,
          "name": "home1",
          "path": "/",
          "permisions": "drwxr-xr-x",
          "link": 3,
          "username": "root",
          "groupname": "root",
          "size": 4096,
          "time": "Jul 31 22:29",
          "isDirectory": true,
          "text": ""
     },
     {
          "id": 4,
          "name": "bin",
          "path": "/",
          "permisions": "lrwxrwxrwx",
          "link": 1,
          "username": "root",
          "groupname": "root",
          "size": 9,
          "time": "Jun  9 09:53",
          "isDirectory": true,
          "text": ""
     },
     {
          "id": 5,
          "name": "boot",
          "path": "/",
          "permisions": "drwxr-xr-x",
          "link": 5,
          "username": "root",
          "groupname": "root",
          "size": 4096,
          "time": "Jul 31 22:32",
          "isDirectory": true,
          "text": ""
     },
     
     ...
]
```


== Running the application locally
  
To launch your tests:
```
./mvnw clean test
```

To run your application:
```
./mvnw spring-boot:run
```

