package ir.maktab.homeservice.exception;
/*
 * created by Amir mahdi seydi 5/9/2022 3:22 AM
 */

public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException(String message) {
            super(message);
    }
}
