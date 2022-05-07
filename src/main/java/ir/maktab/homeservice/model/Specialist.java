package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:43 AM
 */


import ir.maktab.homeservice.exception.UnacceptableException;
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

    @Column(name = "SPECIALITY")
    private String speciality;

    @Column(name = "SCORE")
    private Double score;

    @Column(name = "PROFILE_IMAGE")
    private byte[] profileImage;

    @OneToMany(mappedBy = "specialist"
            , cascade = CascadeType.ALL)
    private Set<SpecialistService> specialistService;

    @OneToMany(mappedBy = "specialist",
            cascade = CascadeType.ALL)
    private Set<Offer> offers;

    @OneToMany(mappedBy = "specialist",
            cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    public void getImage(byte[] profileImage) {
        if (profileImage.length > 300000)
            throw new UnacceptableException("Image Size Should Not Exceed 300 KB!");

        this.profileImage = profileImage;
    }
}
