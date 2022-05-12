package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 5/10/2022 5:10 AM
 */


import ir.maktab.homeservice.model.enumeration.UserState;
import ir.maktab.homeservice.util.SpecSearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SecureUserCriteriaDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Boolean isActive;
    private Date dateOfRegistration;
    private String userName;
    private String mobileNumber;
    private UserState userState;
    private Double score;
    private byte[] profileImage;
    private Boolean isSuperAdmin;

}
