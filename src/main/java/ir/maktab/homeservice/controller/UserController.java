package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/10/2022 4:10 AM
 */

import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.dto.ResetPasswordDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<User>> findAllBySpecification(@RequestParam(value = "search") String search) {
        return new  ResponseEntity<>(userService.findAll(search), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @PutMapping("/giveAccess/{userId}")
    @ResponseBody
    public ResponseEntity<String> giveAccess(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(userService.giveAccessByAdmin(userId), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
        return new ResponseEntity<>(userService.resetPassword(resetPasswordDTO), HttpStatus.OK);
    }


}
