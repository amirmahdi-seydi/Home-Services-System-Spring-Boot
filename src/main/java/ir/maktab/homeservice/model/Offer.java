package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:46 AM
 */


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.maktab.homeservice.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "OFFER")
public class Offer extends BaseEntity<Long> {

    @Column(name = "BID_PRICE",
            nullable = false)
    private BigDecimal bidPrice;

    @JsonFormat(pattern="HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DURATION_OF_JOB",
            nullable = false)
    private Date durationOfJob;

    @JsonFormat(pattern="HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME_OF_JOB",
            nullable = false)
    private Date startTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "DATE_AND_TIME_OF_REGISTER_OFFER",
            nullable = false)
    private Date dateAndTimeOfRegisterOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "SPECIALIST_ID", nullable = false)
    private Specialist specialist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Column(name = "IS_ACCEPTED",
                nullable = false,
                columnDefinition = "smallint")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isAccepted = false;
}
