package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:56 PM
 */


import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.model.Specialist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {

    private Long id;
    private BigDecimal amount;
    private String uuid;
    private Date dateOfTransaction;
    private Customer customer;
    private Specialist specialist;
}
