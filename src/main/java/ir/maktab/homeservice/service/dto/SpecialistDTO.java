package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:54 PM
 */

import ir.maktab.homeservice.model.Offer;
import ir.maktab.homeservice.model.SpecialistService;
import ir.maktab.homeservice.model.Transaction;
import ir.maktab.homeservice.model.enumeration.UserState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecialistDTO {

    private String firstName;
    private String lastName;
    private String userName;
    @NotBlank(message = "Password is mandatory!")
    private String password;
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailAddress;
    private String mobileNumber;
    @NotBlank(message = "Profile image is mandatory!")
    private byte[] profileImage;




}
