package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:57 PM
 */

import ir.maktab.homeservice.model.Order;
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
public class OfferDTO {

    private Long id;
    private BigDecimal bidPrice;
    private Date durationOfJob;
    private Date startTime;
    private Date dateAndTimeOfRegisterOffer;
    private Specialist specialist;
    private Order order;
}
