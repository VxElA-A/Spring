package com.example.DZEight.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
    SecurityFilterChain noSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
                );
        return http.build();
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/home/timesheets/project/{projectId}").hasRole("ADMIN")
//                        .requestMatchers("/home/timesheets/**").hasRole("USER")
//                        .requestMatchers("/timesheets/**").hasRole("REST")
//                        .anyRequest()
//                        .authenticated()
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
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    private final MyCustomUserDetailsService userDetailsService;
//
//    public SecurityConfiguration(MyCustomUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests(authorize -> authorize
//                        .antMatchers("/login", "/css/**", "/js/**").permitAll() // Allow access to login and static resources
//                        .antMatchers("/home/projects/**").hasRole("ADMIN")
//                        .antMatchers("/home/timesheets/**").hasRole("USER")
//                        .antMatchers("/timesheets/**").hasRole("REST")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(withDefaults())
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .authenticationEntryPoint((request, response, authException) -> {
//                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//                        })
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    private final MyCustomUserDetailsService userDetailsService;
//
//    public SecurityConfiguration(MyCustomUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
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
//                .logout(withDefaults())
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .authenticationEntryPoint((request, response, authException) -> {
//                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//                        })
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    private final MyCustomUserDetailsService userDetailsService;
//
//    public SecurityConfiguration(MyCustomUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/home/projects/**").hasRole("ADMIN")
//                        .requestMatchers("/home/timesheets/**").hasRole("USER")
//                        .requestMatchers("/timesheets/**").hasRole("REST")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(withDefaults())
//                .logout(withDefaults())
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .authenticationEntryPoint((request, response, authException) -> {
//                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//                        })
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

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

//@Configuration
//public class SecurityConfiguration {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/home/projects/**").hasRole("ADMIN")
//                        .requestMatchers("/home/timesheets/**").hasRole("USER")
//                        .requestMatchers("/timesheets/**").hasRole("REST")
//                        .anyRequest().authenticated()
//                )
////                .formLogin(form -> form
////                        .loginPage("/login")
////                        .permitAll()
////                )
//                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .permitAll()
//                );
//
//        return http.build();
//
////       return http
////               .authorizeHttpRequests((requests) -> requests
////                       .requestMatchers("/home/**").authenticated()
////                       .anyRequest().permitAll()
////               )
////               .formLogin(Customizer.withDefaults())
////               .build();
//    }
//
//
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//}
