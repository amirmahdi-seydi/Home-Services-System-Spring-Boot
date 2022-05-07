package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:50 AM
 */

import ir.maktab.homeservice.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity<Long> {

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL)
    private Set<Service> services;
}
