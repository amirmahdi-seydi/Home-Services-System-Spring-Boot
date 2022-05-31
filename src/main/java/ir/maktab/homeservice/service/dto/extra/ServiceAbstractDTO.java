package ir.maktab.homeservice.service.dto.extra;
/*
 * created by Amir mahdi seydi 5/11/2022 4:11 AM
 */



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@Getter
@Setter
public class ServiceAbstractDTO {

    private Long id;
    private Long[] specialistId;
    private String name;
    private String description;
    private BigDecimal basePrice;
    private String optionalDescription;
    private String subServiceName;

    public ServiceAbstractDTO(Long[] specialistId, String name) {
        this.specialistId = specialistId;
        this.name = name;
    }

    public ServiceAbstractDTO(Long id, Long[] specialistId, String name, String description, BigDecimal basePrice,
                              String optionalDescription, String subServiceName) {
        this.id = id;
        this.specialistId = specialistId;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.optionalDescription = optionalDescription;
        this.subServiceName = subServiceName;

    }

    public ServiceAbstractDTO(Long id, String name, String description, BigDecimal basePrice,
                              String optionalDescription, String subServiceName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.optionalDescription = optionalDescription;
        this.subServiceName = subServiceName;
    }
}
