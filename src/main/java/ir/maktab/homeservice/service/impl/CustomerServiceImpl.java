package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:12 PM
 */

import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.exception.AlreadyExistException;
import ir.maktab.homeservice.model.*;
import ir.maktab.homeservice.model.enumeration.UserState;
import ir.maktab.homeservice.repository.CustomerRepository;
import ir.maktab.homeservice.service.*;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.CustomerDTO;
import ir.maktab.homeservice.service.dto.UserDTO;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;

import ir.maktab.homeservice.util.CustomPasswordEncoder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, CustomerRepository>
        implements CustomerService {

    private final FinancialCreditService financialCreditService;

    private final UserService userService;


    public CustomerServiceImpl(CustomerRepository repository,
                               FinancialCreditService financialCreditService,
                               UserService userService
    ) {
        super(repository);
        this.financialCreditService = financialCreditService;
        this.userService = userService;

    }

    @Override
    public SecureCustomerDTO save(CustomerDTO customerDTO) {
        if (customerDTO.getId() == null) {
            UserFactory userFactory = new UserFactory();
            User userCustomer = userFactory.getUser("customer");

            if (repository.existsByUserName(customerDTO.getUserName())) {
                throw new AlreadyExistException("This user name already exist!");
            } else if (repository.existsByEmail(customerDTO.getEmail())) {
                throw new AlreadyExistException("This email already exist! ");
            } else if (repository.existsByMobileNumber(customerDTO.getMobileNumber())) {
                throw new AlreadyExistException("This mobile number already exist");
            }

            if (userCustomer instanceof Customer) {
                FinancialCredit financialCredit = new FinancialCredit();
                financialCredit.setBalance(new BigDecimal("0.0"));
                financialCredit = financialCreditService.save(financialCredit);

                Customer customer = (Customer) userCustomer;
                customer.setFirstName(customerDTO.getFirstName());
                customer.setLastName(customerDTO.getLastName());
                customer.setEmail(customerDTO.getEmail());
                customer.setMobileNumber(customerDTO.getMobileNumber());
                customer.setUserState(UserState.PENDING_CONFORMATION);
                customer.setPassword(CustomPasswordEncoder.hashPassword(customerDTO.getPassword()));
                customer.setDateOfRegistration(Date.from(Instant.now()));
                customer.setUserName(customerDTO.getUserName());
                customer.setFinancialCredit(financialCredit);
                customer = repository.save(customer);

                UserDTO userDTO = new UserDTO();
                userDTO.setUserName(customer.getUserName());
                userDTO.setEmailAddress(customer.getEmail());
                userDTO.setId(customer.getId());
                userService.sendConfirmationLink(userDTO);

                return new SecureCustomerDTO(
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail(),
                        customer.getIsActive(),
                        customer.getDateOfRegistration(),
                        customer.getUserName(),
                        customer.getMobileNumber(),
                        customer.getUserState()
                );

            } else {
                return null;
            }
        } else {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            User user = userService.findByUserName(userDetails.getUsername());
            if (!user.getId().equals(customerDTO.getId()) && user.getUserType().equals("customer")) {
                throw new AccessDeniedException("Access denied!");
            }

            Customer customer = repository.getById(customerDTO.getId());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setEmail(customerDTO.getEmail());
            customer.setMobileNumber(customerDTO.getMobileNumber());
            customer.setUserState(UserState.PENDING_CONFORMATION);
            customer.setUserName(customerDTO.getUserName());


            return new SecureCustomerDTO(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getIsActive(),
                    customer.getDateOfRegistration(),
                    customer.getUserName(),
                    customer.getMobileNumber(),
                    customer.getUserState()
            );
        }
    }


    @Override
    public List<SecureCustomerDTO> fetchAllCustomer() {
        return repository.fetchAllCustomerDTOS();
    }


    @Override
    public void deleteById(Long id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
        if (!user.getId().equals(id) && user.getUserType().equals("customer")) {
            throw new AccessDeniedException("Access denied!");
        }
        super.deleteById(id);
    }
}
