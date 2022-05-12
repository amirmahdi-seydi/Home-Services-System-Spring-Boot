package ir.maktab.homeservice.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/*
 * created by Amir mahdi seydi 5/9/2022 5:34 AM
 */


class CustomPasswordEncoderTest {

    public String getPassword(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Test
    void tes() {

        boolean quoted = false;

        String json = "       a   home   service  ";

        StringBuilder builder = new StringBuilder();

        int len = json.length();
        for (int i = 0; i < len; i++) {
            char c = json.charAt(i);
            if (c == ' ')
                quoted = !quoted;

            if (!Character.isWhitespace(c))
                builder.append(c).append(" ");
        }

        System.out.println(builder.toString());

        String categoryName = "         Home          se rvice             ".trim().replaceAll("\\s+", " ");
        System.out.println(categoryName.toUpperCase());
    }



}