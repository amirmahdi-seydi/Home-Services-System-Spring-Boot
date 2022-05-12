package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:38 AM
 */

import ir.maktab.homeservice.model.Offer;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.OfferDTO;
import ir.maktab.homeservice.service.dto.OrderDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOfferDTO;

import java.util.List;


public interface OfferService extends BaseService<Offer,Long> {

    SecureOfferDTO save(OfferDTO offerDTO);

    List<SecureOfferDTO> fetchAllOffer();

    List<Offer> fetchAllOrderOffers();
}
