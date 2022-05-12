package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:07 AM
 */

import ir.maktab.homeservice.model.Offer;
import ir.maktab.homeservice.model.Order;
import ir.maktab.homeservice.service.dto.extra.SecureOfferDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select new ir.maktab.homeservice.service.dto.extra.SecureOfferDTO(" +
            " o.id, o.bidPrice, o.durationOfJob," +
            " o.startTime, o.dateAndTimeOfRegisterOffer, o.specialist.userName)" +
            " from Offer o order by o.id")
    List<SecureOfferDTO> fetchAllOffersDTO();


    @Query(value = "select of from Offer of where of.order_id = :id", nativeQuery = true)
    List<Offer> fetchAllOrderOffers(@Param("id") Long id);
}
