package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:07 AM
 */


import ir.maktab.homeservice.model.Order;
import ir.maktab.homeservice.model.enumeration.OrderState;
import ir.maktab.homeservice.service.dto.extra.SecureOrderDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long>, PagingAndSortingRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("select new ir.maktab.homeservice.service.dto.extra.SecureOrderDTO(" +
            "o.id, o.jobDescription, o.bidPrice, o.orderState," +
            " o.dateAndTimeOfOrderRegistration, o.dateAndTimeOfJob, o.specialistScore," +
            " o.customer.userName, o.service.name, o.address)" +
            " from Order o where o.orderState = :state order by o.id")
    List<SecureOrderDTO> fetchAllAvailableOrdersDTO(@Param("state") OrderState state);


    @Query("select new ir.maktab.homeservice.service.dto.extra.SecureOrderDTO(" +
            "o.id, o.jobDescription, o.bidPrice, o.orderState," +
            " o.dateAndTimeOfOrderRegistration, o.dateAndTimeOfJob, o.specialistScore," +
            " o.customer.userName, o.service.name, o.address)" +
            " from Order o where o.service.name = :service order by o.id")
    List<SecureOrderDTO> fetchOrdersByBaseService(@Param("service") String service);

    /// 4
    @Query("select new ir.maktab.homeservice.service.dto.extra.SecureOrderDTO(" +
            "o.id, o.jobDescription, o.bidPrice, o.orderState," +
            " o.dateAndTimeOfOrderRegistration, o.dateAndTimeOfJob, o.specialistScore," +
            " o.customer.userName, o.service.name, o.address)" +
            " from Order o where o.customer.id = :id order by o.id")
    List<SecureOrderDTO> fullOrdersHistoryCustomer(@Param("id") Long id);

    /// 4
    @Query("select o from Order o join o.offers of where of.specialist.id = :id and of.isAccepted = true ")
    List<Order> fullOrdersHistorySpecialist(@Param("id") Long id);







}
