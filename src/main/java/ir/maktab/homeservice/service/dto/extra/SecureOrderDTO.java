package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 5/11/2022 9:12 PM
 */


import ir.maktab.homeservice.model.Address;
import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.model.Service;
import ir.maktab.homeservice.model.enumeration.OrderState;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;


@Builder
@Getter
@Setter
@AllArgsConstructor
public class SecureOrderDTO {

    private Long id;
    private String jobDescription;
    private BigDecimal bidPrice;
    private OrderState orderState;
    private Date dateAndTimeOfOrderRegistration;
    private Date dateAndTimeOfJob;
    private Double specialistScore;
    private String customerName;
    private String serviceName;
    private Address address;




}
