package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:12 PM
 */

import ir.maktab.homeservice.exception.AlreadyExistException;
import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.UserFactory;
import ir.maktab.homeservice.model.enumeration.UserState;
import ir.maktab.homeservice.repository.CustomerRepository;
import ir.maktab.homeservice.service.CustomerService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.CustomerDTO;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;

import ir.maktab.homeservice.util.CustomPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Date;
import java.util.List;


@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, CustomerRepository>
        implements CustomerService {

    private final UserService userService;

    public CustomerServiceImpl(CustomerRepository repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }


    @Override
    public SecureCustomerDTO save(CustomerDTO customerDTO) {
        UserFactory userFactory = new UserFactory();
        User userCustomer = userFactory.getUser("customer");

        if (repository.existsByUserName(customerDTO.getUserName())) {
            throw new AlreadyExistException("This user name already exist!");
        } else if (repository.existsByEmailAddress(customerDTO.getEmailAddress())) {
            throw  new AlreadyExistException("This email already exist! ");
        } else if (repository.existsByMobileNumber(customerDTO.getMobileNumber())) {
            throw new AlreadyExistException("This mobile number already exist");
        }

        if (userCustomer instanceof Customer) {
            Customer customer = (Customer) userCustomer;
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setEmailAddress(customerDTO.getEmailAddress());
            customer.setMobileNumber(customerDTO.getMobileNumber());
            customer.setUserState(UserState.PENDING_CONFORMATION);
            customer.setHashedPassword(CustomPasswordEncoder.hashPassword(customerDTO.getPassword()));
            customer.setDateOfRegistration(Date.from(Instant.now()));
            customer.setUserName(customerDTO.getUserName());
            customer = repository.save(customer);

            return new SecureCustomerDTO (
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmailAddress(),
                    customer.getIsActive(),
                    customer.getDateOfRegistration(),
                    customer.getUserName(),
                    customer.getMobileNumber(),
                    customer.getUserState()
            );

        } else {
            return null;
        }
    }

    @Override
    public SecureCustomerDTO update(SecureCustomerDTO secureCustomerDTO) {

        if (repository.findById(secureCustomerDTO.getId()).isEmpty()) {
            throw new NotFoundException("Cant find customer with this id!");
    }

        if (repository.existsByUserName(secureCustomerDTO.getUserName())) {
            throw new AlreadyExistException("This user name already exist!");
        } else if (repository.existsByEmailAddress(secureCustomerDTO.getEmailAddress())) {
            throw  new AlreadyExistException("This email already exist! ");
        } else if (repository.existsByMobileNumber(secureCustomerDTO.getMobileNumber())) {
            throw new AlreadyExistException("This mobile number already exist");
        }

        Customer customer = findById(secureCustomerDTO.getId()).get();
        customer.setFirstName(secureCustomerDTO.getFirstName());
        customer.setLastName(secureCustomerDTO.getLastName());
        customer.setEmailAddress(secureCustomerDTO.getEmailAddress());
        customer.setMobileNumber(secureCustomerDTO.getMobileNumber());
        customer.setUserName(secureCustomerDTO.getUserName());
        customer = repository.save(customer);

        return new SecureCustomerDTO (
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress(),
                customer.getIsActive(),
                customer.getDateOfRegistration(),
                customer.getUserName(),
                customer.getMobileNumber(),
                customer.getUserState()
        );

    }

    @Override
    public List<SecureCustomerDTO> fetchAllCustomer() {

        return repository.fetchAllCustomerDTOS();
    }

}
