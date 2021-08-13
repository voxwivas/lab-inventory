# LAB-INVENTORY

A domain Driven approach has been taken and for each of the implemented category tables.

### Project Structure

For each category the code files are arranged in the following packages

- api
- models
- service
- store

<P>The above decision is to ensure that a single category san be worked on without worrying of the other domain parts</P>


There are 3 laboratory categories with attributes supported in the laboratory inventory service as described below.

## Samples Category

- sampleName
- disease
- strainOrBreed
- genotype

## Devices Category

- element
- massInKg
- lengthInMeters
- diameterInMeters
- remarks

## Chemicals Category

- chemicalName
- flammability
- toxicity
- acidity
- reactivity

<p>For each of the categories above there is a corresponding resource endpoint to create, update and list respectively </p>
<p>The project is implemented with Springboot version 2. </p>
<p>The project requires at least Java 11 and above</p>
<p>The dependencies wrapper is maven</p>

### Unit and Integration tests

There is a set of unit and integration tests. To run the tests use the command below.<br/>
<code>mvn test </code>

### Compiling and Building

There are various ways to run the project namely as a <em> self-contained executable .jar </em> or using the <em> maven
spring boot plugin support</em>.

#### Using the self-contained executable

To use this option use the following command in sequence from the project root.

<p>To compile with tests use : <br/><code>mvn clean package</code> </p>
<p>To compile without tests use :<br/> <code>mvn clean package -DskipTests=true </code></p>

<p>After executing either of the above commands use the below command to run the service .<br/><code>java -jar target/lab-experiment-inventory-0.0.1-SNAPSHOT.jar
</code></p>

#### Using the springboot maven plugin

This option is the most straight forward as it only requires the command below on the project root:
<p><code>mvn spring-boot:run</code></p>

## Interacting with the various endpoints

 There is a REST documentation tool already provided with the code base to be found on the project base URL on the following path <code>/swagger-ui.html</code>. 
 
 The interface should look something like the attached images<br/>

![](Swagger-UI.png)

An image showing collapse api contracts

![](Swagger-UI1.png)

Assuming the service is running on <code>localhost:8080</code>  the link below should lead to the api documentation<br/>
 [Swagger UI endpoint](http://localhost:8080/swagger-ui.html)

## Expected enhancements

- Serialize all the Api errors for proper presentation to the front-end
- Add docker support for easy shipping
- Add a docker-compose file for both the service and database layer
- Optimise persistence layer for quicker queries
- Optimise the test cases for short execution times
- Enhance Api documentation beyond the default auto generated description