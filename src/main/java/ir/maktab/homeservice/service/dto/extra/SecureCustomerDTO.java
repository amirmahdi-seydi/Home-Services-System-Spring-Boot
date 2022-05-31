package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 5/8/2022 2:57 AM
 */


import ir.maktab.homeservice.model.enumeration.UserState;
import lombok.*;

import javax.validation.constraints.Pattern;
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

    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    private Boolean isActive;
    private Date dateOfRegistration;
    private String userName;
    private String mobileNumber;
    private UserState userState;
}
