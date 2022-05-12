package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 5/8/2022 2:16 AM
 */

import ir.maktab.homeservice.model.enumeration.UserState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SecureUserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Boolean isActive;
    private Date dateOfRegistration;
    private String userName;
    private String mobileNumber;
    private UserState userState;
}
