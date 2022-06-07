package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:46 AM
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import ir.maktab.homeservice.model.base.BaseEntity;
import ir.maktab.homeservice.model.enumeration.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ORDERS")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Order extends BaseEntity<Long> {

    @Embedded
    private Address address;

    @Column(name = "BID_PRICE")
    private BigDecimal bidPrice;

    @Column(name = "JOB_DESCRIPTION",
            nullable = false)
    private String jobDescription;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ORDER_STATE", nullable = false)
    @Type( type = "pgsql_enum" )
    private OrderState orderState;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "DATE_AND_TIME_OF_ORDER_REGISTRATION",
            nullable = false)
    private Date dateAndTimeOfOrderRegistration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "DATE_AND_TIME_OF_JOB",
            nullable = false)
    private Date dateAndTimeOfJob;

    @Column(name = "SPECIALIST_SCORE", nullable = false)
    private Double specialistScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_ID", nullable = false)
    private Service service;

    @OneToMany(mappedBy = "order",
               cascade = CascadeType.ALL)
    private Set<Offer> offers;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Comment> comments;
}
