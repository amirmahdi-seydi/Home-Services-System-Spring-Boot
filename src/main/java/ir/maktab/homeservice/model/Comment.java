package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:44 AM
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import ir.maktab.homeservice.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COMMENT")
public class Comment extends BaseEntity<Long> {

    @Column(name = "COMMENT_BODY")
    private String commentBody;

    @Column(name = "COMMENT_OWNER")
    private String commentOwner;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "DATE_AND_TIME_OF_REGISTER_COMMENT",
            nullable = false)
    private Date dateAndTimeOfRegisterComment;
}
