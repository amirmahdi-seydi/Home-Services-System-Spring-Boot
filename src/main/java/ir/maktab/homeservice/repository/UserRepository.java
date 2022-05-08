package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:10 AM
 */

import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.enumeration.UserType;
import ir.maktab.homeservice.repository.base.UserBaseRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends UserBaseRepository<User> {

    Boolean existsByUserName(String userName);

    Boolean existsByEmailAddress(String email);

    Boolean existsByMobileNumber(String phoneNumber);

    @Query("select u.userType from User u where u.userName = :userName")
    UserType findUserTypeByUserName(String userName);

    @Query("select u.hashedPassword from User u where u.userName = ?1")
    byte[] findPassword(String userName);

}
