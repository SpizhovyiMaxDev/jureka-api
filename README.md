 # Jureka API

 The goal of this project was to **develop skills in Spring Boot**. This <br />
 is my **first built API**. I initially started working with Spring (without Boot) <br />
 to gain a **basic understanding of how Spring works behind the <br />
 scenes** — how  spring sets up **Tomcat**, manages **ViewResolvers**,<br />
 creates **beans** ect. After the completing **In progress** section planning <br /> to deploy it 
 on AWS.<br />

## API Endpoints

| Method | Endpoint           | Description                    |
|--------|--------------------|--------------------------------|
| GET    | `/api/products`     | Get all products               |
| GET    | `/api/products/{id}`| Get a single product by ID     |
| POST   | `/api/products`     | Create a new product           |
| PUT    | `/api/products/{id}`| Update an existing product     |
| DELETE | `/api/products/{id}`| Delete a product by ID         |

All endpoints return structured error responses in case of failure, <br />
so frontend devs don't have to worry about constructing error messages.


 ## Technologies Used

 - Java 21+
 - Spring Boot
 - Spring Web
 - Spring Data JPA
 - Hibernate
 - Maven
 - Tomcat
 - Jackson
 
  ## In progress 

 - AOP (Aspect-Oriented Programming)
 - Testing


<br />
<br />
<br />