package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 6/3/2022 7:13 AM
 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDetailDTO {
    private Long customerId;
    private Long offerId;
}
