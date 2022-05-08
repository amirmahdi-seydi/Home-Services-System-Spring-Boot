package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:41 AM
 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@DiscriminatorValue("admin")
public class Admin extends User{

    @Column(name = "IS_SUPER_ADMIN",
            columnDefinition = "smallint")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isSuperAdmin = false;
}
