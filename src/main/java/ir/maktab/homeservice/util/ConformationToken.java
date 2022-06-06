package ir.maktab.homeservice.util;
/*
 * created by Amir mahdi seydi 6/4/2022 2:14 AM
 */

import ir.maktab.homeservice.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConformationToken implements Serializable {

    private UserDTO userDTO;
    private Date date;
    private String confirmationToken;

}