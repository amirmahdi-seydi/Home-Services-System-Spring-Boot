package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/9/2022 2:40 AM
 */


import ir.maktab.homeservice.config.CustomAuthenticationProvider;
import ir.maktab.homeservice.config.JwtUtil;
import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.config.security.MyUserDetailsService;
import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.service.dto.extra.SecureUserDTO;
import ir.maktab.homeservice.service.dto.extra.request.AuthenticationRequestDTO;
import ir.maktab.homeservice.service.dto.extra.request.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("api/login")
    public ResponseEntity<?> createAuthenticationToken
            (@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {

        customAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDTO.getUserName(),
                        authenticationRequestDTO.getPassword()
                )
        );
        UserDetails userDetails = myUserDetailsService.
                loadUserByUsername(authenticationRequestDTO.getUserName());
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        String generatedJWT = jwtUtil.generateToken(customUserDetails);

        return new ResponseEntity<>
                (new AuthenticationResponseDTO(generatedJWT, new SecureUserDTO(
                        customUserDetails.getUser().getId(),
                        customUserDetails.getUser().getFirstName(),
                        customUserDetails.getUser().getLastName(),
                        customUserDetails.getUser().getEmailAddress(),
                        customUserDetails.getUser().getIsActive(),
                        customUserDetails.getUser().getDateOfRegistration(),
                        customUserDetails.getUser().getUserName(),
                        customUserDetails.getUser().getMobileNumber(),
                        customUserDetails.getUser().getUserState()
                )), HttpStatus.OK);
    }

}
