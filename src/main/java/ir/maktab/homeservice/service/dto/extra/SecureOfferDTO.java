package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 5/11/2022 10:35 PM
 */


import ir.maktab.homeservice.model.Order;
import ir.maktab.homeservice.model.Specialist;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;


@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
public class SecureOfferDTO {

    private Long id;
    private BigDecimal bidPrice;
    private Date durationOfJob;
    private Date startTime;
    private Date dateAndTimeOfRegisterOffer;
    private String specialistName;


}
