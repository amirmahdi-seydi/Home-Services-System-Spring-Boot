package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:45 AM
 */


import ir.maktab.homeservice.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CREDIT")
public class FinancialCredit extends BaseEntity<Long> {

    @Column(name = "BALANCE")
    private BigDecimal balance;

}
