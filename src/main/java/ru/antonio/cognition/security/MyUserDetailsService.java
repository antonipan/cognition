//package ru.antonio.cognition.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.antonio.cognition.models.User;
//import ru.antonio.cognition.repositories.UserDao;
//
//import java.util.Optional;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user =  userDao.findByUsername(username);
//
//        return user.map(MyUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException
//                        (username + " - user with such name is not found"));
//    }
//}
