package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:51 PM
 */


import ir.maktab.homeservice.model.enumeration.UserState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String emailAddress;
    private String mobileNumber;
    private UserState userState;
    private Date dateOfRegistration;
    private String userType;

}
