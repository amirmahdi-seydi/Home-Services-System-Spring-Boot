package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:39 AM
 */


import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import ir.maktab.homeservice.model.base.BaseEntity;
import ir.maktab.homeservice.model.enumeration.UserState;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
        name = "user_type")
public abstract class User extends BaseEntity<Long> {

    @Column(name = "FIRST_NAME",
            nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME",
            nullable = false)
    private String lastName;

    @Column(name = "USER_NAME",
            nullable = false,
            unique = true)
    private String userName;

    @Column(name = "PASSWORD",
            nullable = false)
    private byte[] password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "DATE_OF_REGISTRATION",
            nullable = false)
    private Date dateOfRegistration;

    @Column(name = "EMAIL_ADDRESS",
            nullable = false,
            unique = true)
    private String emailAddress;

    @Column(name = "MOBILE_NUMBER",
            nullable = false)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "USER_STATE")
    @Type( type = "pgsql_enum" )
    private UserState userState;

    @Column(name = "user_type", insertable = false, updatable = false)
    private String userType;
}
