package ir.maktab.homeservice.model.base;
/*
 * created by Amir mahdi seydi 5/7/2022 10:36 AM
 */


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
@Where(clause = "is_deleted = false")
public abstract class BaseEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private ID id;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
