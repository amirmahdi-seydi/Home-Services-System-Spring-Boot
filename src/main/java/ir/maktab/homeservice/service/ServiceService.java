package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:40 AM
 */

import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.model.Service;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.ServiceDTO;
import ir.maktab.homeservice.service.dto.extra.ServiceAbstractDTO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;


public interface ServiceService extends BaseService<Service, Long> {

    ServiceAbstractDTO save(ServiceAbstractDTO serviceAbstractDTO);

    List<ServiceDTO> fetchAllServices();

    ServiceAbstractDTO saveSpecialist(ServiceAbstractDTO serviceAbstractDTO);

    ServiceAbstractDTO deleteSpecialistByID(ServiceAbstractDTO serviceAbstractDTO);

    List<Service> findSpecialistServices(Long id);

    List<Service> findServiceBy(List<Long> ids);




}
