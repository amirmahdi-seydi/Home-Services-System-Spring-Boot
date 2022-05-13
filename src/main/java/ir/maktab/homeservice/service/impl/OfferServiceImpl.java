package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:19 PM
 */

import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.exception.FinancialBalanceException;
import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.exception.UnacceptableException;
import ir.maktab.homeservice.model.*;
import ir.maktab.homeservice.model.enumeration.OrderState;
import ir.maktab.homeservice.repository.OfferRepository;
import ir.maktab.homeservice.service.OfferService;
import ir.maktab.homeservice.service.OrderService;
import ir.maktab.homeservice.service.SpecialistService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.OfferDTO;
import ir.maktab.homeservice.service.dto.OrderDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOfferDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OfferServiceImpl extends BaseServiceImpl<Offer, Long, OfferRepository>
        implements OfferService {

    private final OrderService orderService;

    private final SpecialistService specialistService;

    private final UserService userService;


    public OfferServiceImpl(OfferRepository repository, @Lazy OrderService orderService,
                            SpecialistService specialistService, UserService userService) {
        super(repository);
        this.orderService = orderService;
        this.specialistService = specialistService;
        this.userService = userService;
    }

    @Override
    public SecureOfferDTO save(OfferDTO offerDTO) {
        if (orderService.findById(offerDTO.getOrderId()).isEmpty()) {
            throw new NotFoundException("There is no order with this id!");
        }
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

         if (offerDTO.getId() == null) {

             Order order = orderService.findById(offerDTO.getOrderId()).get();
             Specialist specialist = specialistService.findById(user.getId()).get();
             ir.maktab.homeservice.model.Service service = order.getService();

             if (repository.existsByOrderIdAndSpecialistId(order.getId(), specialist.getId())) {
                 throw new UnacceptableException("You are already send offer to this order!");
             }
             if (service.getBasePrice().compareTo(offerDTO.getBidPrice()) > 0) {
                 throw new UnacceptableException("You're bid price cant be less than service base price!");
             }

             order.setOrderState(OrderState.W_SPECIALIST_SELECTION);
             order = orderService.save(order);

             Offer offer = new Offer();
             offer.setBidPrice(offerDTO.getBidPrice());
             offer.setDurationOfJob(offerDTO.getDurationOfJob());
             offer.setStartTime(offerDTO.getStartTime());
             offer.setDateAndTimeOfRegisterOffer(Date.from(Instant.now()));
             offer.setSpecialist(specialist);
             offer.setOrder(order);

             offer = repository.save(offer);

             return SecureOfferDTO.builder()
                     .id(offer.getId())
                     .bidPrice(offer.getBidPrice())
                     .durationOfJob(offer.getDurationOfJob())
                     .startTime(offer.getStartTime())
                     .dateAndTimeOfRegisterOffer(offer.getDateAndTimeOfRegisterOffer())
                     .specialistName(offer.getSpecialist().getUserName())
                     .build();

         } else {
             if (!repository.existsById(offerDTO.getId())) {
                 throw new NotFoundException("There is no offer with this id!");
             }

             Offer offer = repository.getById(offerDTO.getId());

             offer.setId(offerDTO.getId());
             offer.setBidPrice(offerDTO.getBidPrice());
             offer.setDurationOfJob(offerDTO.getDurationOfJob());
             offer.setStartTime(offerDTO.getStartTime());

             offer = repository.save(offer);

             return SecureOfferDTO.builder()
                     .id(offer.getId())
                     .bidPrice(offer.getBidPrice())
                     .durationOfJob(offer.getDurationOfJob())
                     .startTime(offer.getStartTime())
                     .dateAndTimeOfRegisterOffer(offer.getDateAndTimeOfRegisterOffer())
                     .specialistName(offer.getSpecialist().getUserName())
                     .build();
         }
    }

    @Override
    public List<SecureOfferDTO> fetchAllOffer() {
        return repository.fetchAllOffersDTO();
    }

    @Override
    public List<Offer> fetchAllOrderOffersAscBidPrice(Long id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            if (customer.getOrders().isEmpty()) {
                throw new NotFoundException("You dont have any offer!");
            }

            return repository.fetchAllOrderOffersAscBidPrice(id, customer.getId());
        } else {
            return null;
        }
    }

    @Override
    public List<Offer> fetchAllOrderOffersDscScore(Long id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            if (customer.getOrders().isEmpty()) {
                throw new NotFoundException("You dont have any offer!");
            }

            return repository.fetchAllOrderOffersDscScore(id, customer.getId());
        } else {
            return null;
        }
    }
}
