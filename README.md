# bank-api-spring-boot

Spring Boot Application Demonstrating activities in Bank Account using Cards

### Components:
- Java
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/guides/index.html)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Lombok](https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok)
- [Jacoco](https://www.eclemma.org/jacoco/)
- [Cucumber](https://cucumber.io/)
- [Swagger](https://swagger.io/)
- [GHERKIN](https://cucumber.io/docs/gherkin/)
- Swagger can be launched in Browser: http://localhost:9010/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
- H2 Console On Browser: http://localhost:9010/h2-console
- Code Coverage: After building the projects you can find code coverage in the target path :-

### Properties
Reading H2 DB related properties from **application.properties** file and configuring JPA connection factory for H2 database.  

    **src/main/resources/application.properties**
    
### Project
    - Assumptions, Requirements and Scope of changes are mentioned in SpringBootMain class
    - Integration test cases are added for business logic using Gherkin and Cucumber
    - Unit Test are added for Controller and some repository methods
