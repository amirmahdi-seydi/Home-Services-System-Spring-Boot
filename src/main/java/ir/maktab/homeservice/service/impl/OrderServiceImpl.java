package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:21 PM
 */

import ir.maktab.homeservice.model.Order;
import ir.maktab.homeservice.repository.OrderRepository;
import ir.maktab.homeservice.service.OrderService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;

public class OrderServiceImpl extends BaseServiceImpl<Order, Long, OrderRepository>
        implements OrderService {

    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }
}
