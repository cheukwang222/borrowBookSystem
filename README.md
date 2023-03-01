# Borrow Books System
**Features**
- View a list of all books
- Search books by using id
- Add new book
- Update book informations
- Delete unused book

**Installation and Usage**

1. Clone the repository to your local machine.
```
git clone https://github.com/cheukwang222/borrowBookSystem.git
```

2. Build the project using Gradle.
```
./gradlew build
```

3. Run the application.
```
./gradlew bootRun
```
4. Default port is 8080, can go to http://localhost:8080 to view the web application.

5. You can also access the Swagger UI documentation by going to [Swagger](http://localhost:8080/swagger-ui/index.html#/).

**API Endpoints**
- GET /api/books - Retrieves a list of all books
- GET /api/books/{id} - Retrieves a specific books
#### Request Parameters
  
Parameter						|Type		|Required	|Description  
:----						|:---		|:------	|:---	
id				|Long 	|Y			| Book identifier

#### Response Example
```
{
  "author": "Test author",
  "borrowed": false,
  "description": "Test description",
  "id": 1,
  "publishDate": "2023-03-01T02:06:52.417Z",
  "title": "Test title"
}
```

- POST /api/books - Create new books
#### Request Parameters
  
Parameter						|Type		|Required	|Description  
:----						|:---		|:------	|:---	
author				    |String 	|N			| Book author
borrowed				|Boolean 	|N			| Book availability
description				|String 	|N			| Book description
publishDate				|Date 	    |N			| Book publish date
title				    |String 	|N			| Book title

#### Response Example
```
{
  "author": "Test author",
  "borrowed": false,
  "description": "Test description",
  "id": 1,
  "publishDate": "2023-03-01T02:06:52.417Z",
  "title": "Test title"
}
```

- DELETE /api/books/{id} - Delete specific books
#### Request Parameters
  
Parameter						|Type		|Required	|Description  
:----						|:---		|:------	|:---	
id				|Long 	|Y			| Book identifier

#### Response Example
```
{
  {200, OK}
}
```

- PUT /api/books/{id} - Update specific books

#### Request Parameters
  
Parameter						|Type		|Required	|Description  
:----						|:---		|:------	|:---	
author				    |String 	|N			| Book author
borrowed				|Boolean 	|N			| Book availability
description				|String 	|N			| Book description
publishDate				|Date 	    |N			| Book publish date
title				    |String 	|N			| Book title

#### Response Example
```
{
  "author": "Test updated author",
  "borrowed": false,
  "description": "Test updated description",
  "id": 1,
  "publishDate": "2023-03-01T02:10:56.417Z",
  "title": "Test updated title"
}
```

**Database connection**

In this project is using H2 database, you can access by [H2 DB](http://localhost:8080/h2-console/) with below login details.
```
Saved Settings: Generic H2 (Embedded)
Setting Name: Generic H2 (Embedded)
  
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password:	
```
