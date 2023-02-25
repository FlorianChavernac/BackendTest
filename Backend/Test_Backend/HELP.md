# Instructions pour lancer l'application 

### Liste des endpoints

 
* Obtenir tout les books
  * GET: http://localhost:8080/all
* Obtenir un book par son id
  * GET: http://localhost:8080/find/1
* Ajouter un book
  * POST: http://localhost:8080/add
* Modifier un book
  * PUT: http://localhost:8080/update/1
* Supprimer un book
* DELETE: http://localhost:8080/delete/1

### Commande pour lancer l'application spring boot

```bash
./gradlew bootRun
```
ou 
directement depuis l'IDE

### Information sur la base de données

* H2
* URL: http://localhost:8080/h2-console
* Username: sa
* Password: password
* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:testdb

### Information sur le projet

* Spring Boot
* Spring Data JPA
* Spring Web
* H2
* Gradle
* Java 17