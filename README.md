# GERIMEDICA CODE-LIST Backend API

## Stack

- Spring Boot 2.6.5
- H2 In-memory Database
- Maven
- Docker
- JDK 18

## Install Instructors

### Pre-requirement
- Docker Engine
- Maven
- JDK 18

### How to initialize the app ?
Opened the terminal and go to the folder which localeted codeList application files then please type command that is in below by whether your chip be M1 or not in Terminal. They will be installed and started app. After all you can use the app by using those postman requests.

- **Important Note** : If have been using Macbook which is based on M1 Chip, It can be lived some issues due to compatibility problems. Please add **--platform=linux/amd64** to following command lines to overcome those issues.

### Build and Deploy application without M1 Chip
``` 
sh ./codeList.sh
```
### Build and Deploy application with M1 Chip
``` 
sh ./codeList.sh DOCKER_DEFAULT_PLATFORM=linux/amd64
```

## Environment Values
The app can be personalized by using environment values. Username and password of Database or Server port number can be specified. Even If they are blank, application will be start with default values. It should be used environment values in below to customize the app.

Please open the sh files with any text editor and you can change environment values in below as whatever want.

- EXTERNAL_PORT = Default value is **8080**. It uses for access to application port. If It is changed to which value, that value have to uses to access application.
- APP_PORT = Default value is **8080**. It is container application inner port. It is not related to external access. It is releated to docker access to application.
- DB_USERNAME = Default value is **admin**. It is uses for username of H2 Database.
- DB_PASSWORD = Default value is **admin**. It is uses for password of H2 Database.

## Swagger UI

It might be found swagger ui url at http://localhost:8080/swagger-ui/index.html#/. Please type it in browser.

# Important Note !

### *Please refresh exercise.csv file while uploading the csv file to server, If you are using example /uploadAllData request in postman samples.*
