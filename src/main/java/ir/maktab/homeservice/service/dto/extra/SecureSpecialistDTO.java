package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 5/8/2022 10:32 PM
 */


import ir.maktab.homeservice.model.enumeration.UserState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SecureSpecialistDTO {

    private Long id;
    private String firstName;
    private String lastName;

    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailAddress;

    private Boolean isActive;
    private Date dateOfRegistration;
    private String userName;
    private String mobileNumber;
    private UserState userState;
    private Double score;
    private byte[] profileImage;
}
