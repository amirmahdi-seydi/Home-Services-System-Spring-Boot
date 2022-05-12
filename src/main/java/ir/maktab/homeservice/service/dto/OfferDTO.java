package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:57 PM
 */


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class OfferDTO {

    private Long id;
    private BigDecimal bidPrice;
    @JsonFormat(pattern="HH:mm")
    private Date durationOfJob;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date startTime;
//    private Date dateAndTimeOfRegisterOffer;
//    private Long specialistId;
    private Long orderId;


}
