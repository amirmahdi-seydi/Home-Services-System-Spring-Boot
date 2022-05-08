package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 4:55 PM
 */

import ir.maktab.homeservice.model.Service;
import ir.maktab.homeservice.repository.ServiceRepository;
import ir.maktab.homeservice.service.ServiceService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;


@org.springframework.stereotype.Service
public class ServiceServiceImpl extends BaseServiceImpl<Service, Long, ServiceRepository>
        implements ServiceService {

    public ServiceServiceImpl(ServiceRepository repository) {
        super(repository);
    }
}
