<h5 align="left">
  <p><img alt="ProvinaBanner" title="#Provina" src="src\main\resources\banner\image.png" /></p>
  <p>Image by <a href= "https://www.instagram.com/agui.nart"/>@Aguinaldofs</a> under the terms of [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/).</p>
</h5>
<h1 align="left">
    provina-socialnetwork-api 📚
</h1>
<h3 align="left">Experimental social network to share old exams and contents of INATEL! 
  </h3>

API Requirements
-----
### Must to have
- [x] Comunicar com uma API externa (desenvolvida pelo colaborador ou por terceiros).
- [x] Persistir dados num BD relacional (não relacional é "nice to have").
- [x] Apresentar alguns testes unitários e funcionais.

### Nice to have
- [ ] Organização do código.
- [ ] Logging.
- [x] Segurança (ex: JWT).
- [x] Cache.

Design Patterns
-----
### Model
Detentor dos dados, recebe as informações do Controller, valida
ou não e retorna a resposta adequada.

### Controller
Fornece a comunicação entre o detentor dos dados e o cliente.

### Repository
Interface de consulta e manipulação dos dados, utilizado para criar uma barreira de controle e segurança entre a aplicação e os dados.

### DTO
Utilizado para transferir dados entre subsistemas do software.

### Form 
Utilizado para transferir dados entre subsistemas do software.

Features
-----

<table border="0" width="100%" 
cellpadding="10">
<tr>
 
<td width="25%" valign="top" border="0">
  
## User
- [x] CREATE
- [x] UPDATE
- [x] LIST
- [x] DELETE

</td>
<td width="25%" valign="top">
  
## Item
- [x] CREATE
- [x] LIST
- [x] DELETE

</td>
<td width="25%" valign="top">
  
## Comment
- [x] CREATE
- [x] LIST
- [x] DELETE

</td>

<td width="25%" valign="top">
  
## Upvote
- [x] CREATE
- [x] DELETE

</td>

</tr>
</table>




Setup
-----
- Clone and open in Eclipse IDE
- Install maven dependencies using IDE auto import or using the command ``mvn install``
- Browse ``http://localhost:8080``
    
API Doc & Sample
----------------

###  Authentication Controller
  
  #### Content-Type
  
    application/json
    
  #### Body
  
    {
      "email": "ag@gec.inatel.br",
      "password": "123456"
    }
    
  #### Paths
  
    POST /auth/login
    
### Item Controller

 #### Content-Type
   ```
   application/json
   Authorization Bearer {{token}}
   ```
 #### Paths
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
  
### User Controller
 
  #### Content-Type
   ```
   application/json
   
   Authorization Bearer {{token}}
   ```
  #### Paths
    
    POST /user
    
   ```
   GET /user/{id}
   ```
   ```
   DELETE /user/{id}
   ```
   
External Tools
----------------

  #### ElephantSQL
  ```
  https://www.elephantsql.com
  ```
  #### Cloudinary
  ```
  https://cloudinary.com
  ```
  #### Swagger
  ```
  https://swagger.io
  ```
  #### Heroku
  ```
  https://www.heroku.com
  ```
  #### Spring Boot Admin
  ```
  https://codecentric.github.io/spring-boot-admin/2.3.1/
  ```

