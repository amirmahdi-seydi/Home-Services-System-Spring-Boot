package ir.maktab.homeservice.service.dto.extra.request;
/*
 * created by Amir mahdi seydi 5/8/2022 5:21 AM
 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDTO {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

}
