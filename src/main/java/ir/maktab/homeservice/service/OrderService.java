package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:37 AM
 */

import ir.maktab.homeservice.model.Comment;
import ir.maktab.homeservice.model.Order;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.CommentDTO;
import ir.maktab.homeservice.service.dto.OfferDTO;
import ir.maktab.homeservice.service.dto.OrderDTO;
import ir.maktab.homeservice.service.dto.extra.PaymentDetailDTO;
import ir.maktab.homeservice.service.dto.extra.RateSpecialistDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOrderDTO;

import java.util.List;


public interface OrderService extends BaseService<Order, Long> {

    SecureOrderDTO save(OrderDTO orderDTO);

    List<SecureOrderDTO> fetchAllAvailableOrders();

    SecureOrderDTO chooseSpecialistOffer(OfferDTO offerDTO);

    SecureOrderDTO changeOrderStatusBySpecialist(Long offerId);

    SecureOrderDTO rateSpecialist(RateSpecialistDTO rateSpecialistDTO);

    Comment addComment(CommentDTO commentDTO);

    List<SecureOrderDTO> seeOrdersByBaseService();

    Boolean payOrderByFinancialCredit(PaymentDetailDTO paymentDetailDTO);

    List<Order> findAll(String search);
}
