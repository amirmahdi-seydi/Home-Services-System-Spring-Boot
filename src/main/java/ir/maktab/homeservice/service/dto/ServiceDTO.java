package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:54 PM
 */


import ir.maktab.homeservice.model.Category;
import ir.maktab.homeservice.model.Order;
import ir.maktab.homeservice.model.SpecialistService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


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
    private Category category;
    private List<Order> orders;
    private List<SpecialistService> specialistService;
}
