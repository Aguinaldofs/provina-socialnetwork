# provina-socialnetwork-api
Experimental social network to share old exams. Created using Spring Boot REST API using jpa (Springboot REST API wich connects MySQL database).

Setup
-----
- Clone and open in Eclipse IDE
- Install maven dependencies using IDE auto import or using the command ``mvn install``
- Browse ``http//localhost:8080``
    
API Doc & Sample
----------------

- Authentication Controller
  
    Content-Type:
    ```
    application/json
    ```
    Body:
    ```
    {
    "email": "ag@gec.inatel.br",
    "password": "123456"
    }
    ```
    Paths:
    ```
    POST /auth/login
    ```
- Item Controller

   Content-Type:
   ```
   application/json
   Authorization Bearer {{token}}
   ```
   Paths:
   ```
  POST /items
  ```
   ```
  POST /items/{id}/comments
  ```
   ```
  POST /items/{id}/upvotes
  ```
   ```
  GET /items
  ```
  ```
  GET /items/{id}
  ```
   ```
  GET /items/{id}/comments
  ```
   ```
  DELETE /items/{id}/comments/{id}
  ```
    ```
  DELETE /items/{id}
  ```
  
   ```
  DELETE /items/{id}/upvotes
  ```
 - User Controller
 
   Content-Type:
   ```
   application/json
   Authorization Bearer {{token}}
   ```
    Paths:
    
    ```
    POST /user
    ```
    ```
    GET /user/{id}
    ```
    ```
    GET /user/{id}/items
    ```
    ```
    DELETE /user/{id}
    ```
