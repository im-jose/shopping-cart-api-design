# MarketPlace API!

This is a simple api created using Spring Boot + PostgreSQL

## Notes

 - The instructions below assumes you have docker installed in your machine
 - The following features were not implemented as this is a test application with a limited scope but are advisable for a real-life application:
	 - Data Validation
	 - CORS
	 - Unit Tests
	 - Spring HATEOAS for hypermedia-driven REST implementation

## Execution Instructions

 1. Unzip the file
 2. Open a terminal & navigate to project location
 3. Execute "mvn clean install"
 4. Execute "mvn clean package"
 5. Execute "docker build ./ -t marketapp"
 6. Execute "docker compose up"

## Queries
In order to test the API you can run the following queries using a client like Insomnia or Postman

### Items Queries

 - List item for sale: 
	 - POST - http://localhost:8080/api/v1/items
	 - Body example:
	 - `{
	"name": "borrador",
	"price": 500,
	"available": true
	}`
 - Get a list of items for sale:
	 - GET - http://localhost:8080/api/v1/items
- Unlist an item for sale
	- DELETE - http://localhost:8080/api/v1/items/{id}
	- Example: http://localhost:8080/api/v1/items/4

 ### Shopping Cart Queries
- Create Shopping Cart
	- POST - http://localhost:8080/api/v1/cart
	- Note: Body is not required
- Add an item to a shopping cart
	- POST - http://localhost:8080/api/v1/cart/{cartId}/item/{itemId}
	- Example: http://localhost:8080/api/v1/cart/5/item/3
- View a user's shopping cart
	- GET - http://localhost:8080/api/v1/cart/{cartId}/item
	- Example: http://localhost:8080/api/v1/cart/5/item
- Remove an item from a shopping cart
	- DELETE - http://localhost:8080/api/v1/cart/5/item/3
 
