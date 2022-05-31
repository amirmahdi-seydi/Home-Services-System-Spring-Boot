package ir.maktab.homeservice.util;
/*
 * created by Amir mahdi seydi 5/8/2022 6:03 AM
 */


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

public class CustomPasswordEncoder {

    private static PasswordEncoder encoder;

    static {
        encoder = new BCryptPasswordEncoder();
    }

    public static byte[] hashPassword(String password) {
        String encode = encoder.encode(password);
        return encode.getBytes(StandardCharsets.UTF_8);
    }

    public static Boolean matches(String oldPassword, String newPassword){
       return encoder.matches(oldPassword, newPassword);
    }

}
