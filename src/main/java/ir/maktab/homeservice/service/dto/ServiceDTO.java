package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/11/2022 3:40 AM
 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal basePrice;
    private String optionalDescription;
    private String subServiceName;
}
