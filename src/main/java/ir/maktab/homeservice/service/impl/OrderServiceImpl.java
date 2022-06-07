package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:21 PM
 */

import com.google.common.base.Joiner;
import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.exception.FinancialBalanceException;
import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.model.*;
import ir.maktab.homeservice.model.enumeration.OrderState;
import ir.maktab.homeservice.model.enumeration.TransactionType;
import ir.maktab.homeservice.model.specification.GenericSpecificationsBuilder;
import ir.maktab.homeservice.model.specification.OrderSpecification;
import ir.maktab.homeservice.repository.OrderRepository;
import ir.maktab.homeservice.service.*;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.CommentDTO;
import ir.maktab.homeservice.service.dto.OfferDTO;
import ir.maktab.homeservice.service.dto.OrderDTO;
import ir.maktab.homeservice.service.dto.extra.PaymentDetailDTO;
import ir.maktab.homeservice.service.dto.extra.RateSpecialistDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOrderDTO;
import ir.maktab.homeservice.util.SearchOperation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@org.springframework.stereotype.Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long, OrderRepository>
        implements OrderService {

    private final CustomerService customerService;

    private final ServiceService serviceService;

    private final UserService userService;

    private final OfferService offerService;

    private final TransactionService transactionService;

    private final CommentService commentService;

    public OrderServiceImpl(OrderRepository repository,
                            CustomerService customerService,
                            ServiceService serviceService,
                            UserService userService,
                            OfferService offerService,
                            TransactionService transactionService,
                            CommentService commentService) {
        super(repository);
        this.customerService = customerService;
        this.serviceService = serviceService;
        this.userService = userService;
        this.offerService = offerService;
        this.transactionService = transactionService;
        this.commentService = commentService;
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
            Service service;

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
            order.setSpecialistScore(null);
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
                    .specialistScore(order.getSpecialistScore())
                    .customerName(order.getCustomer().getUserName())
                    .serviceName(order.getService().getName())
                    .address(order.getAddress())
                    .build();
        } else {

            if (!repository.existsById(orderDTO.getId())) {
                throw new NotFoundException("There is no order register whit is id!");
            }

            Order order = repository.findById(orderDTO.getId()).get();
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
            order.setSpecialistScore(null);
            order.setAddress(address);

            order = repository.save(order);

            return SecureOrderDTO.builder()
                    .id(order.getId())
                    .jobDescription(order.getJobDescription())
                    .bidPrice(order.getBidPrice())
                    .orderState(order.getOrderState())
                    .dateAndTimeOfOrderRegistration(order.getDateAndTimeOfOrderRegistration())
                    .dateAndTimeOfJob(order.getDateAndTimeOfJob())
                    .specialistScore(order.getSpecialistScore())
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
        offer.setIsAccepted(true);
        Order order = offer.getOrder();

        if (user instanceof Customer) {

            if (!order.getCustomer().getId().equals(user.getId())) {
                throw new AccessDeniedException("Access Denied!");
            }

            if (order.getOrderState().equals(OrderState.W_SPECIALIST_SELECTION)) {

                order.setOrderState(OrderState.W_SPECIALIST_COME_PLACE);

            }
        }
        offerService.save(offer);
        order = repository.save(order);
        return SecureOrderDTO.builder()
                .id(order.getId())
                .orderState(order.getOrderState())
                .build();
    }

    @Override
    public SecureOrderDTO changeOrderStatusBySpecialist(Long offerId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        if (offerService.findById(offerId).isEmpty()) {
            throw new NotFoundException("No offer found with this id!");
        }

        Offer offer = offerService.findById(offerId).get();
        Order order = offer.getOrder();

        if (order.getOrderState().equals(OrderState.W_SPECIALIST_COME_PLACE)) {
            order.setOrderState(OrderState.STARTED);

        } else if (order.getOrderState().equals(OrderState.STARTED)) {
            order.setOrderState(OrderState.DONE);

        }
        order = repository.save(order);
        return SecureOrderDTO.builder()
                .id(order.getId())
                .orderState(order.getOrderState())
                .build();
    }

    @Override
    public List<SecureOrderDTO> seeOrdersByBaseService() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        List<ir.maktab.homeservice.model.Service> services = serviceService.findSpecialistServices(user.getId());
        List<SecureOrderDTO> orderDTOList = new ArrayList<>();
        for (ir.maktab.homeservice.model.Service service : services) {
            if (repository.fetchOrdersByBaseService(service.getName()).isEmpty()) {
                throw new NotFoundException("No order found for you!");
            }

            orderDTOList.addAll(repository.fetchOrdersByBaseService(service.getName()));
        }


        return orderDTOList;
    }

    @Override
    public Comment addComment(CommentDTO commentDTO) {

        if (!userService.existsByUserName(commentDTO.getCommentOwnerUserName())) {
            throw new NotFoundException("Somethings Wrong!");
        }

        Comment comment = new Comment();
        comment.setCommentBody(commentDTO.getCommentBody());
        comment.setCommentOwner(commentDTO.getCommentOwnerUserName());
        comment.setDateAndTimeOfRegisterComment(Date.from(Instant.now()));

        comment = commentService.save(comment);

        return comment;
    }

    @Override
    public SecureOrderDTO rateSpecialist(RateSpecialistDTO rateSpecialistDTO) {

        if (repository.findById(rateSpecialistDTO.getOrderId()).isEmpty()) {
            throw new NotFoundException("There is no registered order with this id!");
        }

        Order order = repository.findById(rateSpecialistDTO.getOrderId()).get();

        if (order.getOrderState().equals(OrderState.DONE)) {
            order.setSpecialistScore(rateSpecialistDTO.getRate());
        }

        order = repository.save(order);

        return SecureOrderDTO.builder()
                .specialistScore(order.getSpecialistScore())
                .build();
    }



    @Override
    public Boolean payOrderByFinancialCredit(PaymentDetailDTO paymentDetailDTO) {

        if (offerService.findById(paymentDetailDTO.getOfferId()).isEmpty()) {
            throw new NotFoundException("No offer found!");
        }

        Offer offer = offerService.findById(paymentDetailDTO.getOfferId()).get();
        Order order = offer.getOrder();

        User userCustomer = userService.findById(paymentDetailDTO.getCustomerId()).get();
        User userSpecialist = userService.findById(offer.getSpecialist().getId()).get();

        if (userCustomer instanceof Customer && userSpecialist instanceof Specialist) {
            Customer customer = (Customer) userCustomer;
            Specialist specialist = (Specialist) userSpecialist;

            if (customer.getFinancialCredit().getBalance().compareTo(offer.getBidPrice()) < 0) {
                throw new FinancialBalanceException("You're financial balance is not enough!");
            }

            if (order.getOrderState().equals(OrderState.DONE)) {
                Transaction transaction = new Transaction();
                FinancialCredit financialCredit = customer.getFinancialCredit();
                financialCredit.setBalance(financialCredit.getBalance().subtract(offer.getBidPrice()));
                customer.setFinancialCredit(financialCredit);
                financialCredit = specialist.getFinancialCredit();
                BigDecimal specialistPay = (BigDecimal.valueOf(70)
                        .multiply(BigDecimal.valueOf(100)))
                        .divide(offer.getBidPrice(), RoundingMode.UNNECESSARY);
                financialCredit.setBalance(financialCredit.getBalance().add(specialistPay));
                specialist.setFinancialCredit(financialCredit);
                order.setOrderState(OrderState.PAID);

                repository.save(order);

                transaction.setTransactionType(TransactionType.ACCOUNT_CREDIT);
                transaction.setAmount(offer.getBidPrice());
                transaction.setSpecialist(specialist);
                transaction.setCustomer(customer);
                transaction.setDateOfTransaction(Date.from(Instant.now()));
                transactionService.save(transaction);
            }

            return null;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> findAll(String search) {
        GenericSpecificationsBuilder<Order> builder = new GenericSpecificationsBuilder<>();
        String operationSetExpr = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExpr + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }
        Specification<Order> spec = builder.build(OrderSpecification::new);

        return repository.findAll(spec);
    }
}





