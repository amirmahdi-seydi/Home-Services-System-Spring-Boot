package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:40 AM
 */

import ir.maktab.homeservice.model.Service;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.ServiceDTO;
import ir.maktab.homeservice.service.dto.extra.ServiceAbstractDTO;

import java.util.List;


public interface ServiceService extends BaseService<Service, Long> {

    ServiceAbstractDTO save(ServiceAbstractDTO serviceAbstractDTO);

    List<Service> fetchAllServices();

    ServiceAbstractDTO saveSpecialist(ServiceAbstractDTO serviceAbstractDTO);

    ServiceAbstractDTO deleteSpecialistByID(ServiceAbstractDTO serviceAbstractDTO);
}
