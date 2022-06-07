package ir.maktab.homeservice.config;
/*
 * created by Amir mahdi seydi 5/9/2022 2:44 AM
 */


import ir.maktab.homeservice.config.security.MyUserDetailsService;
import ir.maktab.homeservice.exception.AccountNotActiveException;
import ir.maktab.homeservice.exception.WrongCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final MyUserDetailsService myUserDetailService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(MyUserDetailsService myUserDetailsService,
                                        PasswordEncoder passwordEncoder) {
        this.myUserDetailService = myUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws
            AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        System.out.println(userDetails.getAuthorities());

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    userDetails.getAuthorities()
            );

        } else {
            throw new WrongCredentialsException("Username or password is incorrect!");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class)
                || authentication.equals(AnonymousAuthenticationToken.class);
    }
}
