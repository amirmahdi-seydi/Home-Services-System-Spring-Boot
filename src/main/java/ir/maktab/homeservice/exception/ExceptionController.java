package ir.maktab.homeservice.exception;
/*
 * created by Amir mahdi seydi 5/8/2022 5:15 AM
 */


import ir.maktab.homeservice.service.dto.extra.request.ErrorDTO;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO getAlreadyExistException(AlreadyExistException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
    }

    @ExceptionHandler(WrongCredentialsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO getWrongCredentialsException(WrongCredentialsException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO getMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorDTO getAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.FORBIDDEN,
                ZonedDateTime.now()
        );
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDTO getNoResultsFoundException(NotFoundException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

    }

    @ExceptionHandler(value = AccountNotActiveException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorDTO getAccountNotActiveException(AccountNotActiveException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.FORBIDDEN,
                ZonedDateTime.now()
        );
    }

    @ExceptionHandler(value = UnacceptableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO getUnacceptableException(UnacceptableException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
    }


    @ExceptionHandler(value = DateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO getDateException(DateException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
    }


    @ExceptionHandler(value = IllegalIdentifierException.class)
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public ErrorDTO getIllegalIdentifierException(IllegalIdentifierException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.EXPECTATION_FAILED,
                ZonedDateTime.now()
        );
    }

    @ExceptionHandler(value = FinancialBalanceException.class)
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public ErrorDTO getFinancialBalanceException(FinancialBalanceException ex, WebRequest request) {
        return new ErrorDTO(
                ex.getMessage(),
                HttpStatus.EXPECTATION_FAILED,
                ZonedDateTime.now()
        );
    }

}
