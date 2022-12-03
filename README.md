# bank-api-spring-boot

Spring Boot Application Demonstrating activities in Bank Account using Debit and Credit Cards

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=bugs)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=coverage)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=santhoshvernekar_bank-api-spring-boot&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=santhoshvernekar_bank-api-spring-boot)

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
- [Sonar](https://sonarcloud.io/projects)
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
