### MyRetail RESTful Service

## Case Study
myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.
Build an application that performs the following actions:
* 	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
	Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
* 	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}.
* 	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend 	this is an internal resource hosted by myRetail).
* 	Example: 
	https://redsky-uat.perf.target.com/redsky_aggregations/v1/redsky/case_study_v1?key=3yUxt7WltYG7MFKPp7uyELi1K40ad2ys&tcin=13860428
* 	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 
* 	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s 	price in the data store.

## Technology Stack

*  JDK 11
*  Maven 3.8.5
*  Spring Boot 2.2.4
*  Rest Template
*  Cassandra 4.0.3
*  Junit 5
*  Mockito
*  Postman
*  Swagger
*  Docker 20.10.7
*  Docker Compose 1.29.2


## Getting Started
    
  * Install Java(JDK 11)
  
  * Install Maven  
  
  * Install Docker and make sure docker service is up and running.
  
  * Use git to clone the project.
  
  * Launch cassandra by navigating to cassandra folder in the project and execute "docker-compose up" to setup the datastore.
  
  * Launch the application by navigating into the project root directory and execute "mvn spring-boot:run" command.
  	
##  Swagger2 documentation path
http://localhost:8080/swagger-ui.html
  

### GET Endpoint
 *  Endpoint: http://localhost:8080/v1/products/13264003
 
 *  Sample Response body
 
        {
          "id": 13264003,
          "name": "Jif Natural Creamy Peanut Butter - 40oz",
          "current_price": {
            "value": 8.49,
            "currency_code": "USD"
          }
        }
        
        
### PUT - to update product price
 *  Endpoint: http://localhost:8080/v1/products/13264003
 
 *  Sample Request body
 
        {
          "id": 13264003,
          "name": "Jif Natural Creamy Peanut Butter - 40oz",
          "current_price": {
            "value": 10.59,
            "currency_code": "USD"
          }
        }
        
 *  Sample Response body
 
        	{
		    	"message": "Product price has been updated for 13264003"
		  	}
    
### Further Improvement

 *  Enable OAuth based Security for the API endpoints.

    