package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:43 AM
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {

    @Column(name = "CITY"
            , nullable = false)
    private String city;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "VILLAGE")
    private String village;

    @Column(name = "STREET",
            nullable = false)
    private String street;

    @Column(name = "ALLEY")
    private String alley;

    @Column(name = "HOUSE_NUMBER",
            nullable = false)
    private String houseNumber;
}
