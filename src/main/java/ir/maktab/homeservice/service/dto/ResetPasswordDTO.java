package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/11/2022 3:02 AM
 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResetPasswordDTO {
    private String userName;
    private String newPassword;

}
