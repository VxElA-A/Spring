package com.example.DZSeven;
/**
 *1. Переделать строки RoleName в сущность Role:
 * 1.1 Создать отдельную таблицу Role(id, name)
 * 1.2 Связать User <-> Role отношением ManyToMany
 * 2. После п.1 подправить формирование ролей в MyCustomUserDetailsService
 *
 * В SecurityFilterChain настроить:
 * 3.1 Стандартная форма логина
 * 3.2 Страницы с проектами(projects) доступы пользователям с ролью admin
 * 3.2 Страницы с таймшитами(timesheets) доступы пользователям с ролью user
 * 3.3 REST-ресурсы доступны пользователям с ролью rest
 *
 * **** Для rest-ресурсов НЕ показывать форму логина.
 * Т.е. если пользователь не авторизован, то его НЕ редиректит на форму логина, а сразу показывается 401.
 * Для авторизации нужно отдельно получить JSESSIONID и подставить в запрос.
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DZfiveApplication {

    public static void main(String[] args) {


        SpringApplication.run(DZfiveApplication.class, args);



//        ApplicationContext ctx = SpringApplication.run(DZfiveApplication.class, args);
//
//        UserRepository userRepository =ctx.getBean(UserRepository.class);
//
//        initializeData(userRepository);



//        userRepository.save(new User("admin", "admin"));
//        userRepository.save(new User("user", "user"));
//        userRepository.save(new User("rest", "rest"));

//        User admin = new User();
//        admin.setLogin("admin");
//        admin.setPassword("admin");
//
//        User user = new User();
//        user.setLogin("user");
//        user.setPassword("user");
//
//        User rest = new User();
//        rest.setLogin("rest");
//        rest.setPassword("rest");
//
//        userRepository.saveAll(List.of(admin, user, rest));

    }

//    private static void initializeData(UserRepository userRepository) {
//        if (userRepository.count() == 0) {
//            User admin = new User("admin", "admin");
//            User user = new User("user", "user");
//            User rest = new User("rest", "rest");
//
//            userRepository.saveAll(List.of(admin, user, rest));
//        }
//    }

}
