package com.movieticket.config;

import com.movieticket.dao.LoginUser;
import com.movieticket.dao.UserRepository;
import com.movieticket.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

            return user.map(LoginUser::new)
                    .orElseThrow(()->new UsernameNotFoundException("user not found" +username));
//        LoginUser
//        if(user == null){
//            throw new UsernameNotFoundException("Could not Foound");
//        }else{
//            return (UserDetails) user;
//
//        }
    }
}
