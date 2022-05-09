package ir.maktab.homeservice.config;
/*
 * created by Amir mahdi seydi 5/8/2022 2:50 AM
 */


import ir.maktab.homeservice.config.security.MyUserDetailsService;
import ir.maktab.homeservice.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserServiceDetail;

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(MyUserDetailsService myUserServiceDetail, JwtRequestFilter jwtRequestFilter) {
        this.myUserServiceDetail = myUserServiceDetail;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserServiceDetail);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);*/
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
