# LAB-INVENTORY 

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
<code>mvn  test </code>

### Compiling and Building

There are various ways to run the project namely as a <em> self-contained executable .jar </em> or using the <em> maven spring boot plugin support</em>.

#### Using the self-contained executable

To use this option use the following command in sequence from the project root.

<p>To compile with tests use : <br/><code>mvn clean package</code> </p>
<p>To compile without tests use :<br/> <code>mvn clean package -DskipTests=true </code></p>

<p>After executing either of the above commands use <code>java -jar target/lab-experiment-inventory-0.0.1-SNAPSHOT.jar
</code> to run the service</p>


#### Using the springboot maven plugin
This option is the most straight forward as it only requires the command below on the project root:
<p><code>mvn spring-boot:run</code></p>

## Interacting with the various endpoints
 <p>There is a REST documentation tool already provided with the copde base to be found on the project base URL on the following path <code>/swagger-ui.html</code>. 
    The interface should look something like the attached image<br/>
    ![Swagger screen shot](Swagger-UI.png)
</p>