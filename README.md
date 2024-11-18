# book-catalog-api
### A catalogue service that provides a list of all catalogued books, add new books, update existing information and remove books.


## How to run the service:
### To run the service on your computer clone the repository. After that, execute the following maven commands on your cmd:

- mvn install to install dependencies and build project
- mvn spring-boot:run to run the server

It uses the h2 in-memory so you do not have to worry about database settings and installation.

This can easily be done with IntelliJ IDE. Without using the commands.

The default port is *3000* already set in the application.properties file. So to access the api go to *localhost:3000*

Available endpoints are:

```
POST localhost:3000/api/books - create a book

GET localhost:3000/api/books - get all books

PUT localhost:3000/api/books/{id} - update a book

DELETE localhost:3000/api/books/{id} - delete a book
```

Visit the swagger documentation on http://localhost:3000/swagger-ui/index.html#/ for the request and response fields.
