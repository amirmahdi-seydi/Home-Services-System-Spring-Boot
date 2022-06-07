package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/9/2022 2:40 AM
 */


import ir.maktab.homeservice.config.CustomAuthenticationProvider;
import ir.maktab.homeservice.config.JwtUtil;
import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.config.security.MyUserDetailsService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.dto.extra.SecureUserDTO;
import ir.maktab.homeservice.service.dto.extra.request.AuthenticationRequestDTO;
import ir.maktab.homeservice.service.dto.extra.request.AuthenticationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/authentication")
    public ResponseEntity<AuthenticationResponseDTO> createAuthenticationToken
            (@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {

        return new ResponseEntity<>
                (userService.loginRequest(authenticationRequestDTO), HttpStatus.OK);
    }
}


