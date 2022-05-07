package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:47 AM
 */

import ir.maktab.homeservice.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SERVICE")
public class Service extends BaseEntity<Long> {

    @Column(name = "name",
            nullable = false)
    private String name;

    @Column(name = "description",
            nullable = false)
    private String description;

    @Column(name = "base_price",
            nullable = false)
    private BigDecimal basePrice;

    @Column(name = "optional_description",
            length = 200)
    private String optionalDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CETEGORY_ID", nullable = false)
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SERVICE_SPECIALIST",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "specialist_id"))
    private Set<Specialist> specialists;

    @OneToMany(mappedBy = "service",
            cascade = CascadeType.ALL)
    private Set<Order> orders;
}
