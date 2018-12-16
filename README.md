# craftdemo

## Description/Problem Statement:
This project is to demonstrate the design for solving a problem where we got to import two very large (petabyte) size databases that may have duplicate customer data. The output should be a graph having a given type of entity as vertex and all the similar business entities of the same type added as an edge in that graph. Essentially a depiction of potentially matching data.

A few things to note:
1. There are no correlating IDs that connect the records from these two databases. Duplicate records will need to be identified using a probabilistic matching algorithm.
2. The data constraints (e.g. name lengths) and format (e.g. phone number format) for the columns vary for the 2 tables

## Assumptios and Declarations:

- This project is a demo project and is just a mock of how a production data pipeline may be built to solve such a problem.
- Unit/Integration tests are yet to be written and checked-in. Due to time constraint only manual happy path functional testing was performed to see that the project works end to end with the given input and outputs the required output.
- Very minimal validations are performed. In fact the only validations that might be there are performed by the Jackson library that validates the data while reading the CSV files. A lot of validations could be added at so many different places in so many different services. But due to the time constraint and to limit the scope of this assignment it has been assumed that those validations could be added later if required.

## Tools & technologies Used:
- Java 1.8
- Spring Boot 2.1.1
- Maven 4.0.0
- Git 2.x

## What tools and technologies may be used for various services in production:
- Hadoop - for distributed file storage and processing
- Spark- for distributed computing
- Kafka - for distributed queue, as a databus for transfer form one component to another.
- Couchbase - for distributed in memory cache or storage for faster access
- Neo4J - for graph DB

## How to run this project
1. Note make sure that you have the required softwares(java, maven, git) already installed on your system.
2. Download and unzip the source repository for this demo project
   `git clone https://github.com/amit-kumar-sharma/craftdemo.git`
3. Let's say you have downloaded it to your Demo_Home(say D:\demo) directory.
4. Go to the directory that contains the pom.xml. In this case it will be in 
   $Demo_Home\craftdemo\sbggraph\
5. Run the following maven command:
   `mvn spring-boot:run`
6. This will start the demo app as a spring boot web application.
7. Now go to your web browser and go to the following URL:
   `http://localhost:8085/sbggraph/index`
   > Note: if the above URL is not working you might be having a port conflict on your system in that case, please update the port mentioned in the [application.properties](https://github.com/amit-kumar-sharma/craftdemo/blob/master/sbggraph/src/main/resources/application.properties)
   `server.port=8085`
8. Now you should be seeing a page similar to the following:
   ![Home Page](images/DatabaseImportPage.png?raw=true)
9. Copy the sample DB files [orion_db.csv](https://github.com/amit-kumar-sharma/craftdemo/blob/master/sbggraph/src/test/resources/orion_db.csv) and [magellan_db.csv](https://github.com/amit-kumar-sharma/craftdemo/blob/master/sbggraph/src/test/resources/magellan_db.csv) present in [test/resources](../sbggraph/src/test/resources). Fill these file paths in the respective text boxes present in the above input page and click the "submit" button.
10. After submitting those two files the application will present you with a link to go check the results of your DB ingestion. As shown below click on the that link:
   ![Page after Submission](images/DatabaseImportPostSubmitPage.png?raw=true)
11. Clicking on this link will take you the result page that will show you the processed results that the application found out to be similar as shown below:
   ![Page after Submission](images/ResultPage.png?raw=true)
## High Level Architecture Diagram for the data pipeline:

![Architecture Diagram](images/architecture_diagram.png?raw=true "Architecture/Component Diagram")

## Sequence Diagrams for the major services:

### Sequence Diagram for Rest Import Controller that receives the DB files from the Input page:

![Data Import Service](images/DataImportInputController.png?raw=true "Data Import Service")

### Sequence Diagram for Data Import Service:

![Data Import Service Task](images/DataImportTask.png?raw=true "Data Import Service Task")

### Sequence Diagram for Data Processing Service:

![Data Processing Service](images/OnlineDataProcessingService.png?raw=true "Data Processing Service")

### Sequence Diagram for Data Processing Task:

![Data Processing Task](images/OnlineDataProcessingTask.png?raw=true "Data Processing Task")

## Class Diagrams:

![Class diagrams](images/ClassDiagrams.png?raw=true "Class diagrams")
