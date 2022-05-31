package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:04 AM
 */

import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.repository.base.UserBaseRepository;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerRepository extends UserBaseRepository<Customer> {

    @Query("select new ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO(" +
            "c.id, c.firstName, c.lastName, c.email," +
            " c.isActive, c.dateOfRegistration,c.userName, c.mobileNumber, c.userState)" +
            " from Customer c order by c.id")
    List<SecureCustomerDTO> fetchAllCustomerDTOS();
}
