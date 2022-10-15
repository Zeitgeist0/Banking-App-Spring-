The project is deployed here:
http://banking-app-final.herokuapp.com/
Heroku may take 10 seconds to load the application. If there is an error, you just need to reload the page
To log in use these credentials:
1. Username: user / Password: user
2. Username: admin / Password: admin

The complete source code of this project is located at the banking-app-final folder. The other folders/versions have only half of the implementations, one of them was created to practice with pure SQL and JDBC, and the other one uses Hibernate ORM. 

///Technical specifications of banking-app-final
In order to run this app locally, it is needed to import the module using the pom file, and then use the Maven plugin to execute spring-boot:run. It may be needed to run clean/package before.
Technologies used:  Spring Boot, Spring Data/JPA, DAO/Service/Controller design pattern,  REST API, DTO mapping,  Spring security, Auditing, AOP, Slf4j logging, websockets and React + React Browser.

/Project building
1. The front-end/React part is built in this project, it is located at the \src\main\front-end folder.  During the Spring packaging phase, the configuration automatically executes "npm run build" and copies the result to the target folder used to build the Java WAR file. So there is no need to manually copy the React Build folder into the Java source code folder, all this happens automatically thanks to the "frontend-maven-plugin".
2. The final output is a WAR file which includes all the dependencies, so it is ready to be deployed anywhere.
3. The project was deployed to Heroku, using the heroku-maven-plugin and webapp-runner. Basically we just need to git push it to heroku, and the project will be automatically built and run by webapp-runner.

/Back-end specifications
1. There are 3 main entities: Customer, Account and Employer. One customer can have many accounts (OneToMany and ManyToOne relationship). While many employers can have many customers, as one customer can work for different companies (ManyToMany relationship).
2. It was used Spring Data/JPA for the ORM. The entities were created in the "Domain" folder, then using the JpaRepository a basic CRUD was created for them ("DAO" layer). When needed I added custom queries using JPA. Then in the Service layer I implemented the CRUD provided by the JPA repositoy, and also wrote the logic for some custom queries like  making fund transfers between accounts. Then the Controller layer ("Resources" folder)  provides all the endpoints.
3. In the domain/dto folder I created a DTO class for the response and the request of each entity. Then using the ModelMapper and the DtoMapperFacade, each time any endpoint is called, our Model maps the body of the request and creates a DTO object representation of the entity that we received.This means that there is a total controll on what fields are used/needed to create a new entity, and what fields are sent as a response when we receive for example a GET request. There is also a strict validation on the information that we receive to create a new entity, for example there are fields which can not be null, or other fields like the email must meet a specific criteria using regex. And when sending a response to the GET request, there are fields which are ommited, for example the user password. 
4. All entities are audited by the JpaAuditing, which means that when a record is modified, it will be stored the information about when it was modified. Also it is stored the information about when a record was created.
5. Using Slf4j and AOP all controllers are being logged, and we can see what information was sent to the endpoint, and what information we send as a response.
6. Global error handling implemented with GlobalControllerAdvice.
7. Spring security is enabled for this project, and all endpoints are protected until the user is authorized. Any request will be redirected to /login if the session is not authorized. 
8. Websockets (STOMP and Sock.JS) are used in this project, and they are automatically connected once we log in. Then, when we transfer funds between accounts, there will be a pop up window displayed informing whether the transaction was successfull or not. This is because we were subsribed to the queue "/queue/user", and when the Controller provides a response, it also sends a message to this queue.
9. There are 2 databases connected to this project. One of them is H2, which is used in the "dev" profile. It is built in the memory of the application and it is really fast and useful for development. The other one is used when we set the "prod" profile, and it connects us to a Postgre database provided by heroku.
10. Using the create-drop DDL specification, each time we run the application, all tables are deleted and then all entities are created according to our classes. The data is loaded automatically with SQL queries.

/Front-end specifications
1. React was used for this project. The different sections mapping was created with ReactBrowser, so we can easily navigate between them.
2. In the API folder I wrote the functions that will call the endpoints.
3. Each time we load a new section, for example Customers,  the useEffect hook automatically calls the endpoint that provides the list of all customers, and they are loaded into a list of Customers.
4. Loading spinner was added because the Postgre database provided by heroku has low performance as it is free. During the development phase it was used the H2 database and everything was loaded much faster.
5. ReactToastify was used for the pop-un windows that are displayed by the websockets.
