package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 5/30/2022 10:16 PM
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.File;


@NoArgsConstructor
@Getter
@Setter
public class SpecialistAbstractDTO {

    private Long id;
    private Long[] servicesId;
    private String firstName;
    private String lastName;
    private String userName;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Length must at least 8")
    @NotBlank(message = "Password is mandatory!")
    private String password;

    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Please provide a valid email address")
    private String email;

    private String mobileNumber;

    //  @NotBlank(message = "Profile image is mandatory!")
    private File profileImage;
}
