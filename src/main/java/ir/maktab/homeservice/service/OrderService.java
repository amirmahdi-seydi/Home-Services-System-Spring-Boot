package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:37 AM
 */

import ir.maktab.homeservice.model.Order;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.OfferDTO;
import ir.maktab.homeservice.service.dto.OrderDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOrderDTO;

import java.util.List;


public interface OrderService extends BaseService<Order, Long> {

    SecureOrderDTO save(OrderDTO orderDTO);

    List<SecureOrderDTO> fetchAllAvailableOrders();

    SecureOrderDTO chooseSpecialistOffer(OfferDTO offerDTO);
}
