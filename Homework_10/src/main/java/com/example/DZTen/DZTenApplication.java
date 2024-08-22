package com.example.DZTen;

/**
 *Протестировать TimesheetController
 * GET /timesheets/{id}
 * GET /timesheets
 * POST /timesheets
 * DELETE /timesheets
 * PUT /timesheets/{id}
 */



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DZTenApplication {

    public static void main(String[] args) {


        SpringApplication.run(DZTenApplication.class, args);



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
