

1. @RequestParam
Аннотация @RequestParam используется для привязки параметров HTTP-запроса к параметрам метода контроллера. 
Она позволяет Spring автоматически извлекать значение параметра из URL и передавать его в метод контроллера.

        1.1 value
        Параметр value указывает имя параметра запроса, который будет привязан к аргументу метода. 
        Например, если у вас есть URL http://localhost:8080/home?type=vip, параметр type из URL будет привязан к аргументу метода, 
        помеченному @RequestParam("type").
        
        1.2 defaultValue
        Параметр defaultValue указывает значение по умолчанию, которое будет использоваться, если параметр запроса отсутствует в URL. 
        Это полезно для установки значений по умолчанию, когда пользователь не передает определенные параметры в запросе.

Пример:
В методе home аннотация @RequestParam(value = "type", defaultValue = "regular") означает следующее:

java:
```java
@GetMapping("/home")
public List<Ticket> home(@RequestParam(value = "type", defaultValue = "regular") String type) {
    // ...
}
```

value = "type": Ожидается, что в URL будет параметр с именем type. Например, http://localhost:8080/home?type=vip.
defaultValue = "regular": Если параметр type не передан в запросе, будет использовано значение по умолчанию "regular".

С параметром:
URL: http://localhost:8080/home?type=vip
Значение type будет равно "vip".

Без параметра:
URL: http://localhost:8080/home

Значение type будет равно "regular" (значение по умолчанию).
Примеры URL:
http://localhost:8080/home?type=vip: Параметр type будет равен "vip".

http://localhost:8080/home?type=regular: Параметр type будет равен "regular".

http://localhost:8080/home: Параметр type не указан, поэтому будет использоваться значение по умолчанию "regular".
Пример кода:
```java
@GetMapping("/home")
public List<Ticket> home(@RequestParam(value = "type", defaultValue = "regular") String type) {
    List<Ticket> tickets = new ArrayList<>();
    for (int i = 1; i <= 5; i++) {
        tickets.add(scoreboard.newTicket(type));
    }
    return tickets;
}
```
В этом примере, в зависимости от значения параметра type, метод будет создавать список тикетов определенного типа. 
Если параметр type не указан в запросе, будет использоваться значение "regular".