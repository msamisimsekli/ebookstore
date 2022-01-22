# ebookstore
Online book retail system

## Architecture

There are 3 domain model classes

1. Book - Represents the book with its quantity in the storage
2. Customer - Represents the customer which can order books.
3. EBookStoreOrder - Represents a customer order having list of books and their total price.

There are 4 controller

1. BookController - Able to save a new book to system and retrieve books from the system.
2. CustomerController - Able to save a new customer to system and retrieve customers from the system.
3. OrderController - Able to create a new order, retrieve a customer's all orders, retrieve a specific order and retrieve orders by date interval
4. StatisticsController - Able to get monthly order statistics for customer(s) 

### Technology Stack
Spring Boot, H2, Docker

### How To Run

Prereq: Docker should be installed in your system.

After navigating the folder where Dockerfile exists, you can run these commands:

`docker build -t ebookstore:latest`

`docker run -p 8181:8181 ebookstore:latest`

### Postman Requests

They can be found in the file `EBookApplication.postman_collection`
If post requests are sent in order, there will not be any change in any ID in request params.
