package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/10/2022 4:10 AM
 */

import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<User>> findAllBySpecification(@RequestParam(value = "search") String search) {
        return new  ResponseEntity<>(userService.findAll(search), HttpStatus.OK);
    }
}
