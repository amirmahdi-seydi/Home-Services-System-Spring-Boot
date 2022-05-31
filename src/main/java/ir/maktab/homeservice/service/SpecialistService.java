package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:35 AM
 */

import ir.maktab.homeservice.model.Service;
import ir.maktab.homeservice.model.Specialist;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.ServiceDTO;
import ir.maktab.homeservice.service.dto.SpecialistDTO;
import ir.maktab.homeservice.service.dto.extra.SecureSpecialistDTO;
import ir.maktab.homeservice.service.dto.extra.SpecialistAbstractDTO;

import java.util.List;


public interface SpecialistService extends BaseService<Specialist, Long> {

    SecureSpecialistDTO save(SpecialistAbstractDTO specialistDTO);

    List<SecureSpecialistDTO> fetchAllSpecialist();

    User findByUserName(String userName);

    Boolean existsByUserName(String userName);

    List<Specialist> findSpecialistBy(List<Long> ids);

    SpecialistAbstractDTO addServiceToSpecialistSkills(SpecialistAbstractDTO specialistAbstractDTO);

    @Override
    void deleteById(Long id);
}
