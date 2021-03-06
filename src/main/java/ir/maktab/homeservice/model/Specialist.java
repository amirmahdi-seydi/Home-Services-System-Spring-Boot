package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:43 AM
 */


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@DiscriminatorValue("specialist")
public class Specialist extends User {

    @Column(name = "SCORE")
    private Double score;

    @Column(name = "PROFILE_IMAGE")
    private byte[] profileImage;

    @JsonIgnore
    @OneToMany(mappedBy = "specialist",
            cascade = CascadeType.ALL)
    private Set<ServiceSpecialist> serviceSpecialist;

    @OneToMany(mappedBy = "specialist",
            cascade = CascadeType.ALL)
    private Set<Offer> offers;

    @OneToMany(mappedBy = "specialist",
            cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPECIALITY_ID")
    private SubService speciality;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "financial_credit_id", referencedColumnName = "id")
    private FinancialCredit financialCredit;


}
