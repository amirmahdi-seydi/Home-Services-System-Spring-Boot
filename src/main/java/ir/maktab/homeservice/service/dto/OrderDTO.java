package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:57 PM
 */


import com.fasterxml.jackson.annotation.JsonFormat;
import ir.maktab.homeservice.model.*;
import ir.maktab.homeservice.model.enumeration.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;
    private Long customerId;
    private Long serviceId;
    private String jobDescription;
    private BigDecimal bidPrice;
    private OrderState orderState;
    private Date dateAndTimeOfOrderRegistration;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateAndTimeOfJob;
    private Double specialistScore;
    private String city;
    private String district;
    private String village;
    private String street;
    private String alley;
    private String houseNumber;
}
