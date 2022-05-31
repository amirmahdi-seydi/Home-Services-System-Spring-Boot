package ir.maktab.homeservice.service.dto.extra.request;
/*
 * created by Amir mahdi seydi 5/9/2022 4:39 AM
 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequestDTO {

    private String username;
    private String password;
    private Boolean remember;

}
