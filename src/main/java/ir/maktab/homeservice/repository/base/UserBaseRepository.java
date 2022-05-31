package ir.maktab.homeservice.repository.base;
/*
 * created by Amir mahdi seydi 5/7/2022 10:56 AM
 */

import ir.maktab.homeservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface UserBaseRepository<E extends User> extends JpaRepository<E, Long> {

    E findByUserName(String userName);

    Boolean existsByEmail(String email);

    Boolean existsByMobileNumber(String phoneNumber);

    E findByMobileNumber(String phoneNumber);

    Boolean existsByUserName(String userName);
}
