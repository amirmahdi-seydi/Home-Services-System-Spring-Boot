package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/7/2022 4:56 PM
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private Long id;
    private String commentBody;
    private Date dateAndTimeOfRegisterComment;
}
