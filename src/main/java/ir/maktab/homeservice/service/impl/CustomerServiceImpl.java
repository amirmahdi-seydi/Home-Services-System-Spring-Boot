package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:12 PM
 */

import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.repository.CustomerRepository;
import ir.maktab.homeservice.service.CustomerService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;

public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, CustomerRepository>
        implements CustomerService {

    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }
}
