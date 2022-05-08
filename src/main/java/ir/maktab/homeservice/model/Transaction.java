package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:48 AM
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import ir.maktab.homeservice.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TRANSACTION")
public class Transaction extends BaseEntity<Long> {

    @Column(name = "amount",
            nullable = false)
    private BigDecimal amount;

    @Column(name = "UUID",
            nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "DATE_OF_TRANSACTION",
            nullable = false)
    private Date dateOfTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPECIALIST_ID", nullable = false)
    private Specialist specialist;

}
