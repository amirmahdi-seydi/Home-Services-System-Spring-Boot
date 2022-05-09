package ir.maktab.homeservice.config.security;
/*
 * created by Amir mahdi seydi 5/9/2022 2:03 AM
 */

import ir.maktab.homeservice.exception.WrongCredentialsException;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final HttpServletRequest request;

    public MyUserDetailsService(UserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userService.findByUserName(username);
        String loggingUserType = request.getHeader("user-type");

        if (user != null) {
            if(!loggingUserType.equals(user.getUserType()))
                throw new WrongCredentialsException("Username or password is incorrect!");

            return new CustomUserDetails(user);
        } else {
            throw new WrongCredentialsException("Username or password is incorrect!");
        }
    }
}
