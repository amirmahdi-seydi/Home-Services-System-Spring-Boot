package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 5/8/2022 2:57 AM
 */


import ir.maktab.homeservice.model.enumeration.UserState;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SecureCustomerDTO {

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
