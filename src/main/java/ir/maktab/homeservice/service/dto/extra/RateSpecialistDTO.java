package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 6/1/2022 10:48 PM
 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RateSpecialistDTO {
    private Long orderId;
    private Double rate;
}
