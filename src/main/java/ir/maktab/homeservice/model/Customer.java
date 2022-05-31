package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:42 AM
 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@DiscriminatorValue("customer")
public class Customer extends User {

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL)
    private Set<Order> orders;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "financial_credit_id", referencedColumnName = "id")
    private FinancialCredit financialCredit;
}
