package com.example.DZSeven.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final MyCustomUserDetailsService userDetailsService;

    public SecurityConfiguration(MyCustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/home/timesheets/project/{projectId}").hasRole("ADMIN")
                        .requestMatchers("/home/timesheets/**").hasRole("USER")
                        .requestMatchers("/timesheets/**").hasRole("REST")
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                )
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder() {
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                System.out.println("Raw password: " + rawPassword);
//                System.out.println("Encoded password: " + encodedPassword);
//                boolean matches = super.matches(rawPassword, encodedPassword);
//                System.out.println("Password match result: " + matches);
//                return matches;
//            }
//        };
//    }
}



//@Configuration
//public class SecurityConfiguration {
//
//    private final MyCustomUserDetailsService userDetailsService;
//
//    public SecurityConfiguration(MyCustomUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/home/projects/**").hasRole("ADMIN")
//                        .requestMatchers("/home/timesheets/**").hasRole("USER")
//                        .requestMatchers("/timesheets/**").hasRole("REST")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .permitAll()
//                )
//                .userDetailsService(userDetailsService);
//
//        return http.build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder() {
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                System.out.println("Raw password: " + rawPassword);
//                System.out.println("Encoded password: " + encodedPassword);
//                boolean matches = super.matches(rawPassword, encodedPassword);
//                System.out.println("Password match result: " + matches);
//                return matches;
//            }
//        };
//    }
//}

//@Configuration
//public class SecurityConfiguration {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/home/projects/**").hasRole("ADMIN")
//                        .requestMatchers("/home/timesheets/**").hasRole("USER")
//                        .requestMatchers("/timesheets/**").hasRole("REST")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}


