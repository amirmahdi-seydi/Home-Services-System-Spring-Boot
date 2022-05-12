package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/9/2022 2:40 AM
 */


import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.dto.extra.request.AuthenticationRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/login")
    public ResponseEntity<?> createAuthenticationToken
            (@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        return new ResponseEntity<>(userService.loginRequest(authenticationRequestDTO), HttpStatus.OK);
    }

}
