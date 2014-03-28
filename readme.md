Mongozly
============================================
A demo app to help as a template for REST with Java

This project is simple template for doing basic operations using the best of breed technologies.
Technologies used:

- Java
- Grizzly (A very fast NIO server)
- Jersey (JAX-RS) REST provider
- Mongo the leading NoSQL database
- Hibernate Validator (markup only)
- Moxy as JSON provider
- Morphia as a POJO mapper
- JUnit

The basic functionality is very straightforward.

App.java contains that starting point.
Mongo uses a local connection and a collection "users" in db "mongozly".

Navigating to localhost:8080/rest/users/username will print the JSON data for the user.
JSON format is shown in db/jsonTestData which is used for the JUnit tests

- Moxy is used for very easy translation from POJO to JSON and back.
- Hibernate validation markup is used but the validation part is missing.
- Morphia is used as a datastore for doing the actual DAO work.

Note that grizzly is used super-lightly here without the servlet configurations
which are not needed in this example implementation.

Tests
The tests input this data into a test-db and delete it after each test. Only the DAO is tested but you can extend
testing the Endpoints should also be implemented if you want to be thorough.