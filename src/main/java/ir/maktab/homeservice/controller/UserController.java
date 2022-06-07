package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/10/2022 4:10 AM
 */

import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.service.UserActivationService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.dto.ResetPasswordDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserActivationService userActivationService;

    public UserController(UserService userService,
                          UserActivationService userActivationService) {
        this.userService = userService;
        this.userActivationService = userActivationService;
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


    @ResponseBody
    @GetMapping("/confirm-account")
    public ResponseEntity<Boolean> confirmAccount(@RequestParam Map<String, String> requestParam) {
        String confirmation = requestParam.get("token");
        String userId = requestParam.get("userId");
        System.out.println(userId);
        return new ResponseEntity<>(userActivationService.confirm(confirmation, userId), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
        return new ResponseEntity<>(userService.resetPassword(resetPasswordDTO), HttpStatus.OK);
    }


}
