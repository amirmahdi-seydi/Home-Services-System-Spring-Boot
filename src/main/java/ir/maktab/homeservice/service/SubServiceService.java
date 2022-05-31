package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:36 AM
 */

import ir.maktab.homeservice.model.SubService;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.SubServiceDTO;

import java.util.List;


public interface SubServiceService extends BaseService<SubService, Long> {

    @Override
    SubService save(SubService subService);

    SubService findSubServiceByName(String name);

    Boolean existBySubServiceName(String name);

    List<SubServiceDTO> fetchAllSubServiceNames();


}
