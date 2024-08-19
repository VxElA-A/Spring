package com.example.DZEight.Security;

import com.example.DZEight.Repository.UserRepository;
import com.example.DZEight.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyCustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Trying to load user: " + username);
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("User found: " + user.getLogin());

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                authorities
        );
    }
}





//package com.example.DZfive.Security;
//
//import com.example.DZfive.Model.User;
//import com.example.DZfive.Repository.UserRepository;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.stereotype.Service;
//
//
//import java.util.List;
//
//import java.util.stream.Collectors;
//
//
//@Service
//public class MyCustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public MyCustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("Trying to load user: " + username);
//        User user = userRepository.findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        System.out.println("User found: " + user.getLogin());
//
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
//                .collect(Collectors.toList());
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                authorities
//        );
//    }
//}

//@Service
//public class MyCustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public MyCustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("Trying to load user: " + username);
//        User user = userRepository.findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        System.out.println("User found: " + user.getLogin());
//
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
//                .collect(Collectors.toList());
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                authorities
//        );
//    }
//}

//@Service
//@RequiredArgsConstructor
//public class MyCustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
//
//        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
//                .collect(Collectors.toList());
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                authorities
//        );
//    }
//}

//@Component
//@RequiredArgsConstructor
//public class MyCustomUserDetailsService implements UserDetailsService {
//
////    @Autowired
////    UserDetailsService userDetailsService;
//
//    // user храняться БД в таблице users
//    private final UserRepository userRepository;
//
//    // Implement the loadUserByUsername method
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        return userRepository.findByLogin(username)
////                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
////        Optional<User> user = userRepository.findByLogin(username);
////        user.orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден"));
//
//        User user = userRepository.findByLogin(username)
//                .orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден"));
//
//        // TODO: использовать роли пользователеей
//     return new org.springframework.security.core.userdetails.User(user.getLogin()
//             , user.getPassword()
//             , List.of(new SimpleGrantedAuthority("ROLE_USER")));
////     return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), List.of());
//
////        return null;
//    }
//
//
//
//}
