package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:21 PM
 */

import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.exception.FinancialBalanceException;
import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.model.*;
import ir.maktab.homeservice.model.enumeration.OrderState;
import ir.maktab.homeservice.repository.OrderRepository;
import ir.maktab.homeservice.service.*;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.OfferDTO;
import ir.maktab.homeservice.service.dto.OrderDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOrderDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long, OrderRepository>
        implements OrderService {

    private final CustomerService customerService;

    private final ServiceService serviceService;

    private final UserService userService;

    private final OfferService offerService;

    private final TransactionService transactionService;

    public OrderServiceImpl(OrderRepository repository, CustomerService customerService,
                            ServiceService serviceService, UserService userService,
                            @Lazy OfferService offerService, TransactionService transactionService) {
        super(repository);
        this.customerService = customerService;
        this.serviceService = serviceService;
        this.userService = userService;
        this.offerService = offerService;
        this.transactionService = transactionService;
    }

    @Override
    public SecureOrderDTO save(OrderDTO orderDTO) {

        if (serviceService.findById(orderDTO.getServiceId()).isEmpty()) {
            throw new NotFoundException("There is no sub service with this id!");
        }

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        if (orderDTO.getId() == null) {

            Customer customer;
            ir.maktab.homeservice.model.Service service;

            customer = customerService.findById(user.getId()).get();
            service = serviceService.findById(orderDTO.getServiceId()).get();

            Address address = new Address();
            address.setCity(orderDTO.getCity());
            address.setDistrict(orderDTO.getDistrict());
            address.setStreet(orderDTO.getStreet());
            address.setAlley(orderDTO.getAlley());
            address.setVillage(orderDTO.getVillage());
            address.setHouseNumber(orderDTO.getHouseNumber());

            Order order = new Order();
            order.setJobDescription(orderDTO.getJobDescription());
            order.setBidPrice(orderDTO.getBidPrice());
            order.setOrderState(OrderState.W_SPECIALIST_SUGGESTION);
            order.setDateAndTimeOfOrderRegistration(Date.from(Instant.now()));
            order.setDateAndTimeOfJob(orderDTO.getDateAndTimeOfJob());
            order.setScore(null);
            order.setAddress(address);
            order.setCustomer(customer);
            order.setService(service);

            order = repository.save(order);

            return SecureOrderDTO.builder()
                    .id(order.getId())
                    .jobDescription(order.getJobDescription())
                    .bidPrice(order.getBidPrice())
                    .orderState(order.getOrderState())
                    .dateAndTimeOfOrderRegistration(order.getDateAndTimeOfOrderRegistration())
                    .dateAndTimeOfJob(order.getDateAndTimeOfJob())
                    .score(order.getScore())
                    .customerName(order.getCustomer().getUserName())
                    .serviceName(order.getService().getName())
                    .address(order.getAddress())
                    .build();
        } else {

            if (!repository.existsById(orderDTO.getId())) {
                throw new NotFoundException("There is no order register whit is id!");
            }

            Order order = repository.getById(orderDTO.getId());
            Address address = new Address();
            address.setCity(orderDTO.getCity());
            address.setDistrict(orderDTO.getDistrict());
            address.setStreet(orderDTO.getStreet());
            address.setAlley(orderDTO.getAlley());
            address.setVillage(orderDTO.getVillage());
            address.setHouseNumber(orderDTO.getHouseNumber());

            order.setId(order.getId());
            order.setJobDescription(orderDTO.getJobDescription());
            order.setBidPrice(orderDTO.getBidPrice());
            order.setOrderState(OrderState.W_SPECIALIST_SUGGESTION);
            order.setDateAndTimeOfOrderRegistration(Date.from(Instant.now()));
            order.setDateAndTimeOfJob(orderDTO.getDateAndTimeOfJob());
            order.setScore(null);
            order.setAddress(address);

            order = repository.save(order);

            return SecureOrderDTO.builder()
                    .id(order.getId())
                    .jobDescription(order.getJobDescription())
                    .bidPrice(order.getBidPrice())
                    .orderState(order.getOrderState())
                    .dateAndTimeOfOrderRegistration(order.getDateAndTimeOfOrderRegistration())
                    .dateAndTimeOfJob(order.getDateAndTimeOfJob())
                    .score(order.getScore())
                    .customerName(order.getCustomer().getUserName())
                    .serviceName(order.getService().getName())
                    .address(order.getAddress())
                    .build();
        }
    }

    @Override
    public List<SecureOrderDTO> fetchAllAvailableOrders() {
        return repository.fetchAllAvailableOrdersDTO(OrderState.W_SPECIALIST_SUGGESTION);
    }

    @Override
    public SecureOrderDTO chooseSpecialistOffer(OfferDTO offerDTO) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        if (offerService.findById(offerDTO.getId()).isEmpty()) {
            throw new NotFoundException("No offer found with this id!");
        }

        Offer offer = offerService.findById(offerDTO.getId()).get();
        Order order = offer.getOrder();

        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            Specialist specialist = offer.getSpecialist();


            if (!order.getCustomer().getId().equals(user.getId())) {
                throw new AccessDeniedException("Access Denied!");
            }

            if (order.getOrderState().equals(OrderState.W_SPECIALIST_SELECTION)) {

                if (customer.getFinancialCredit().getBalance().compareTo(offer.getBidPrice()) < 0) {
                    throw new FinancialBalanceException("You're financial balance is not enough!");
                }

                order.setOrderState(OrderState.W_SPECIALIST_COME_PLACE);

            } else if (order.getOrderState().equals(OrderState.W_SPECIALIST_COME_PLACE)) {
                order.setOrderState(OrderState.STARTED);

            } else if (order.getOrderState().equals(OrderState.STARTED)) {
                order.setOrderState(OrderState.DONE);

            } else if (order.getOrderState().equals(OrderState.DONE)) {
                Transaction transaction = new Transaction();
                FinancialCredit financialCredit = customer.getFinancialCredit();
                financialCredit.setBalance(financialCredit.getBalance().subtract(offer.getBidPrice()));
                customer.setFinancialCredit(financialCredit);
                financialCredit = specialist.getFinancialCredit();
                financialCredit.setBalance(financialCredit.getBalance().add(offer.getBidPrice()));
                specialist.setFinancialCredit(financialCredit);
                order.setOrderState(OrderState.PAID);

                transaction.setAmount(offer.getBidPrice());
                transaction.setSpecialist(specialist);
                transaction.setCustomer(customer);
                transaction.setDateOfTransaction(Date.from(Instant.now()));


            }
        }
        order = repository.save(order);
        return SecureOrderDTO.builder()
                .id(order.getId())
                .orderState(order.getOrderState())
                .build();
    }
}
