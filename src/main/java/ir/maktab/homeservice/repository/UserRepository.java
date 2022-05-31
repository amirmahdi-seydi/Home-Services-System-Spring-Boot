package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:10 AM
 */

import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.enumeration.UserType;
import ir.maktab.homeservice.repository.base.UserBaseRepository;
import ir.maktab.homeservice.service.dto.extra.SecureUserCriteriaDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends UserBaseRepository<User>, JpaSpecificationExecutor<User> {

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    Boolean existsByMobileNumber(String phoneNumber);

    @Query("select u.userType from User u where u.userName = :userName")
    UserType findUserTypeByUserName(String userName);

    @Query("select u.password from User u where u.userName = ?1")
    byte[] findPassword(String userName);

}
