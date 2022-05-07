package ir.maktab.homeservice.model;
/*
 * created by Amir mahdi seydi 5/7/2022 10:49 AM
 */

public class UserFactory {

    public User getUser(String userType) {

        if (userType.equalsIgnoreCase("CUSTOMER"))
            return new Customer();
        else if(userType.equalsIgnoreCase("ADMIN")) {
            return new Admin();
        } else if(userType.equalsIgnoreCase("SPECIALIST")) {
            return new Specialist();
        } else {
            return null;
        }
    }
}
