# Final-Project-Christina-Ablanska-JMP1

                                                                  Проект чат с Server-Sent Events.

                                                                    
Направете прост чат Spring boot application, който да използва SSE.https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-async-sse

Идеята логнати потребители да могат да изпращат съобщения по между си.
Ако user1 прати съобщение до user2, то вашия Rest service трябва да потърси дали user2 e логнат, ако да вашия Rest service праща съобщението чрез SseEmitter.send до user2, ако не логнат, то вашия Rest service записва съобщението за user2 в една sql pending таблица . Идеята да има отделен service, които да проверява за даден user има ли чакащи (не изпратени съобщения) към него.


Напишете Rest services  на java .
       Използвайте Spring , NamedParameterJdbcOperations.
       Използвайте модела @RestController- >@Service -> @Repository
       Напишете тестове (тестовете са задължителни).
       Използвайте mockMvc  и asyncDispatch за тестовете. 

