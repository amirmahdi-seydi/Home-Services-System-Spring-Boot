package ir.maktab.homeservice.service.dto.extra.request;
/*
 * created by Amir mahdi seydi 5/9/2022 4:41 AM
 */


import ir.maktab.homeservice.service.dto.extra.SecureUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponseDTO {
    private String jwt;
    private SecureUserDTO user;
}
