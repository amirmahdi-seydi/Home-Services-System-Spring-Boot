package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:19 PM
 */

import ir.maktab.homeservice.model.Offer;
import ir.maktab.homeservice.repository.OfferRepository;
import ir.maktab.homeservice.service.OfferService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;

public class OfferServiceImpl extends BaseServiceImpl<Offer, Long, OfferRepository>
        implements OfferService {

    public OfferServiceImpl(OfferRepository repository) {
        super(repository);
    }
}
