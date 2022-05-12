package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:35 AM
 */

import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.CustomerDTO;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;

import java.util.List;


public interface CustomerService extends BaseService<Customer, Long> {

    SecureCustomerDTO save(CustomerDTO customerDTO);

    List<SecureCustomerDTO> fetchAllCustomer();

    @Override
    void deleteById(Long id);
}
