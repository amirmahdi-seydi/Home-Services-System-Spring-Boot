package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:57 PM
 */


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
    private Address address;
    private BigDecimal bidPrice;
    private String jobDescription;
    private OrderState orderState;
    private Date dateAndTimeOfOrderRegistration;
    private Date dateAndTimeOfJob;
    private Double score;
    private Customer customer;
    private Service service;
    private List<Offer> offers;
    private List<Comment> comments;
}
