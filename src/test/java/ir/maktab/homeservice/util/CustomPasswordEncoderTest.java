package ir.maktab.homeservice.util;

import com.github.javafaker.Faker;
import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.model.enumeration.UserState;
import ir.maktab.homeservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

/*
 * created by Amir mahdi seydi 5/9/2022 5:34 AM
 */


class CustomPasswordEncoderTest {
    private static PasswordEncoder encoder;

    static {
        encoder = new BCryptPasswordEncoder();
    }

    static Faker faker = new Faker();



    public void getCustomer() {
        Customer customer = new Customer();
        customer.setFirstName(faker.name().firstName());
        customer.setLastName(faker.name().lastName());
        customer.setEmail(faker.internet().emailAddress());
        customer.setDateOfRegistration(Date.from(Instant.now()));
        customer.setPassword(CustomPasswordEncoder.hashPassword(faker.internet().password()));
        customer.setMobileNumber(faker.phoneNumber().phoneNumber());
        customer.setUserName(faker.name().username());
        customer.setUserState(UserState.PENDING_CONFORMATION);
        customer.setIsActive(true);
        System.out.println(customer.toString());


    }

    public String getPassword(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Test
    void tes() {
        System.out.println(getPassword((CustomPasswordEncoder.hashPassword("Ams1377+"))));
        System.out.println(Arrays.toString(CustomPasswordEncoder.hashPassword("Ams1377+")));

        System.out.println(encoder.matches("Ams1377+", getPassword((CustomPasswordEncoder.hashPassword("Ams1377+")))));
    }



}