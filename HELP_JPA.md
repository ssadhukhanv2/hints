# Getting Started


### Useful Queries:
* SELECT * FROM USER;
* SELECT * FROM CONTENT;
* SELECT * FROM INFORMATION;
* SELECT * FROM HINT;


### Author Observations:
***
#### Unable to lazily load objects [Example-information.getContent()] from inside CommandlineRunner.run()
Ans: **@Transactional Annotation** solves the issue (Check after few months)


### Questions
* How does **JdbcTemplate**, **CrudRepository** & **JPARepository** relate??
* Custom Implementation for Spring Data Repositories
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations


### Important Lessons:
* NOTE When you want to store a floating-point value accurately and make sure you don’t lose decimal precision when executing various operations with the values, use BigDecimal and not double or float.
https://learning.oreilly.com/library/view/spring-start-here/9781617298691/Text/ch12.htm#sigil_toc_id_104
* JPA & Spring Data doubts figured out: 
  * **Spring Data** has **Spring Data JPA** & **Spring Data JDBC**
  * **JDBCTemplate** template use **DataSource**(Concrete Impl provided by Hikari)
  * **Hikari** is the default connection pool for Springboot
  * **Spring Data** & **JPA** are different
  * **Hibernate** is an tool for ORM which implements **JPA**
  * **JPA** (javax.persistence API) is specification as defined by **Jakarta EE**
  * **EntityManager** is inside **JPA**(javax.persistence API provided by **JakartaEE**)
  * **Session** implements **EntityManager**
  * **Spring by default** uses **Hibernate** as the default **JPA vendor**.(Other JPA Vendor includes EclipseLink)
* **Jakarta EE** formerly Java Platform, Enterprise Edition and Java 2 Platform Enterprise Edition is a set of specifications, extending Java SE with specifications for enterprise features such as distributed computing and web services.

### Useful Links:
* Hikari Configurations: https://github.com/brettwooldridge/HikariCP#gear-configuration-knobs-baby
* Spring Data JPA: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface
* Spring Data JDBC: https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#reference
* Custom Implementation for Spring Data Repositories
  * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations
* How does Hibernate & JPA relate
* EntityManager vs Session
* How to create own EntityManager
https://stackoverflow.com/questions/5640778/hibernate-sessionfactory-vs-jpa-entitymanagerfactory
https://www.baeldung.com/hibernate-entitymanager
https://www.baeldung.com/spring-open-session-in-view

    JPA:
    javax.persistence.EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("com.baeldung.movie_catalog");
    javax.persistence.EntityManager em = emf.createEntityManager();

    Hibernate:
    EntityManagerFactory.createEntityManager() 	is used to create the entityManager
    SessionFactoryImpl implements EntityManagerFactory, so SessionFactoryImpl.createEntityManager() will exist
    SessionFactoryImpl(Concrete Class) -> SessionFactoryImplementor -> SessionFactory -> EntityManagerFactory
    So basically SessionFactoryImpl can be used to create to create EntityManager
    javax.persistence.EntityManager em = org.hibernate.internal.SessionFactoryImpl.createEntityManager();// This returns Session
    Session -> EntityManager


* How EntityManager works under the hood
  * Persistence.createEntityManagerFactory() is used to create the EntityManagerFactory, 
  * EntityManagerFactory.createEntityManager() is used to create the entity manager 
  * https://www.baeldung.com/hibernate-entitymanager
* How to send a mail in java to Gmail, when "Allow less secure apps: OFF"
  * https://stackoverflow.com/questions/35550064/how-to-send-a-mail-in-java-to-gmail-when-allow-less-secure-apps-off
  * https://developers.google.com/api-client-library/java/google-api-java-client/oauth2
  * https://stackoverflow.com/questions/38159384/i-want-to-use-gmail-within-my-website-to-send-and-receive-mails-how-can-i-achie
* Send emails from Apps: https://www.baeldung.com/spring-email
* Authentication Failed for less secure apps:
  * https://stackoverflow.com/questions/23137012/535-5-7-8-username-and-password-not-accepted  
* Enable Authetication for less secure Apps:
  * https://myaccount.google.com/lesssecureapps?pli=1&rapt=AEjHL4MbKxuU1qglWw5Oj0wsw4xMlVsxZPsOIWAfmiHBFT-5yNn3Ei_ENuVUKdarIXW3n7dBwCqfuhrJ_IMMPa0F3pafMxpDBg
* Why DAO or Respository bean can be singleton in Spring
  * http://shengwangi.blogspot.com/2016/01/why-dao-or-respository-bean-can-be-singleton-in-spring.html

* Prototype beans in real-world scenarios
https://learning.oreilly.com/library/view/spring-start-here/9781617298691/Text/ch05.htm#sigil_toc_id_62
* why @EnableAspectJAutoProxy is not required for spring-boot
  * https://stackoverflow.com/questions/48625149/spring-aop-works-without-enableaspectjautoproxy
* AOP Documentation: 
  * https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-aspectj-support
* Lazy loading results in exception from command line Runner https://stackoverflow.com/questions/32269192/spring-no-entitymanager-with-actual-transaction-available-for-current-thread
* Unable to access entity manager from command line runner: https://stackoverflow.com/questions/38726193/spring-boot-jpa-error-why-am-i-not-able-to-use-command-line-runner-to-persist-a
* Difference @javax.persistence.Id vs @org.springframework.data.annotation.Id
  * https://stackoverflow.com/questions/39643960/whats-the-difference-between-javax-persistence-id-and-org-springframework-data
* JPA/Hibernate Cascade Types:
  * https://www.baeldung.com/jpa-cascade-types
* @Table Annotation (hibernate): 
  * https://stackoverflow.com/questions/37729770/what-is-the-use-of-table-annotation-in-jpa
  * https://docs.oracle.com/javaee/5/api/javax/persistence/Table.html
* Check this project from mapping: https://github.com/codeforgeyt/jpa-relationships
* What type of collection to be used in JPA: https://stackoverflow.com/questions/4655392/which-java-type-do-you-use-for-jpa-collections-and-why
* Modelling Many-to-Many Relationships: https://www.baeldung.com/jpa-many-to-many
* Constructors in Lombok: https://projectlombok.org/features/constructor
* How to create custom query using spring data: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
* Service Layer vs DAO Layer: https://softwareengineering.stackexchange.com/questions/220909/service-layer-vs-dao-why-both
* DAO package Structure: https://stackoverflow.com/questions/9981277/dao-package-structure
* Component Scanning: https://www.baeldung.com/spring-component-scanning
* Persistent data storage using H2: https://www.baeldung.com/h2-embedded-db-data-storage
* Initial database configuration using data.sql & schema.sql file: https://www.baeldung.com/spring-boot-data-sql-and-schema-sql
* More Detailed documentation on how to load data: https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts
* Difference between JDBC, JdbcTemplate, JPA (contains EntityManager), Spring Data JPA (contains Repository) https://stackoverflow.com/questions/14621495/what-is-the-difference-between-an-spring-entity-manager-and-spring-data-reposito
* Implement Google Search: https://www.journaldev.com/7207/google-search-from-java-program-example

### Reference Documentation Generated By Spring Initializer
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

