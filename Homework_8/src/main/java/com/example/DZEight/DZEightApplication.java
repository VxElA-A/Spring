package com.example.DZEight;

/**
 * 1. В LoggingAspect добавить логирование типов и значений аргументов.
 * Например (пример вывода): TimesheetService.findById(Long = 3)
 * Эту информацию можно достать из joinPoint.getArgs()
 *
 * 2. * Создать аспект, который аспектирует методы, помеченные аннотацией Recover, и делает следующее:
 * 2.1 Если в процессе исполнения метода был exception (любой),
 * то его нужно залогировать ("Recovering TimesheetService#findById after Exception[RuntimeException.class, "exception message"]")
 * и вернуть default-значение наружу Default-значение: для примитивов значение по умолчанию, для ссылочных типов - null.
 * Для void-методов возвращать не нужно.
 *
 * 3. **** В аннотацию Recover добавить атрибут Class<?>[] noRecoverFor, в которое можно записать список классов исключений,
 * которые НЕ нужно отлавливать.
 * Это вхождение должно учитывать иерархию классов.
 *
 * Пример:
 * @Recover(noRecoverFor = {NoSuchElementException.class, IllegalStateException.class})
 * public Timesheet getById(Long id) {...}
 *
 */

//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.METHOD)
//public @interface Recover { // recover - восстанавливать
////    Class<?>[] noRecoverFor() default {};
//}
//}


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DZEightApplication {

    public static void main(String[] args) {


        SpringApplication.run(DZEightApplication.class, args);



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
