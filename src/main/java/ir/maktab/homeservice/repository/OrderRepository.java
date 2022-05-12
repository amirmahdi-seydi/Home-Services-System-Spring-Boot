package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:07 AM
 */


import ir.maktab.homeservice.model.Order;
import ir.maktab.homeservice.model.enumeration.OrderState;
import ir.maktab.homeservice.service.dto.extra.SecureOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select new ir.maktab.homeservice.service.dto.extra.SecureOrderDTO(" +
            "o.id, o.jobDescription, o.bidPrice, o.orderState," +
            " o.dateAndTimeOfOrderRegistration, o.dateAndTimeOfJob, o.score," +
            " o.customer.userName, o.service.name, o.address)" +
            " from Order o where o.orderState = :state  order by o.id")
    List<SecureOrderDTO> fetchAllAvailableOrdersDTO(@Param("state") OrderState state);


}
