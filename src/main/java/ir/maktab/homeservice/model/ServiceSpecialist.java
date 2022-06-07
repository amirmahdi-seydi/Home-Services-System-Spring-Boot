package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 3:56 PM
 */

import ir.maktab.homeservice.model.base.BaseEntity;
import lombok.*;

import javax.naming.Name;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "SPECIALIST_SERVICE")
public class ServiceSpecialist extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialist_id", nullable = false)
    private Specialist specialist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;


}
