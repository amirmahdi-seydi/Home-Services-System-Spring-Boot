package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:56 PM
 */


import ir.maktab.homeservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FinancialCreditDTO {

    private Long id;
    private BigDecimal balance;
    private User user;
}
