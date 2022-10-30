Please execute mvn clean install

**To run**

java -jar target/file-processor-1.0.1.jar target/classes/orders.csv target/classes/orders2.json

**Output**

{&quot;orderId&quot;:3, &quot;amount&quot;:1.23, &quot;currency&quot;: &quot;USD&quot;, &quot;comment&quot;: &quot;order payment&quot;}
{&quot;orderId&quot;:4, &quot;amount&quot;:1.24, &quot;currency&quot;: &quot;EUR&quot;, &quot;comment&quot;: &quot;order payment&quot;}