Java EE 7 DEMO för Oracle WebLogic Server 12.2.1

Weblogic documentation: 
https://docs.oracle.com/middleware/1221/wls/index.html

Java EE 7 tutorial:
https://docs.oracle.com/javaee/7/tutorial/index.html

EJB included in Web Profile:
https://en.wikipedia.org/wiki/Java_EE_version_history#Java_EE_7_(June_12,_2013)

- pom.xml
- StartupEjb
- JAXRSConfiguration ersätter web.xml
- EchoService använder JSON API och bean validation

unset http_proxy && BASE_URL="http://localhost:7001/jee/rest"
curl -H'Content-Type: application/json' -d'{"message":"hello!"}' "$BASE_URL/echo"

- RestLogFilter
- RestPreMatchingFilter, @PreMatching

curl "$BASE_URL/APAping"

- @Inject Logger (@ApplicationScoped)
- LoggerProducer, @Produces
- ThreadNamingServletFilter, ingen web.xml
- TimeServiceV1 + TimeEjbDao + DaoLoggerInterceptor + TransactionObserver (events)

curl "$BASE_URL/time/v1"
curl "$BASE_URL/time/v1/1"

- TimeServiceV2 + ContainerManagedEmDao + @RequestScoped! + @Transactional: 
curl "$BASE_URL/time/v2"
curl "$BASE_URL/time/v2/1"

- TimeServiceV3 + ApplicationManagedEmDao + EntityManagerProducer(@RequestScoped): 

curl "$BASE_URL/time/v3"
curl "$BASE_URL/time/v3/1"
