package ir.maktab.homeservice.service.dto.extra.request;
/*
 * created by Amir mahdi seydi 5/9/2022 8:28 PM
 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangePasswordDTO {

    private String oldPassword;
    private String newPassword;
}
