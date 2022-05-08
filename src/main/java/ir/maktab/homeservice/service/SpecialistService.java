package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:35 AM
 */

import ir.maktab.homeservice.model.Specialist;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.SpecialistDTO;
import ir.maktab.homeservice.service.dto.extra.SecureSpecialistDTO;


public interface SpecialistService extends BaseService<Specialist, Long> {

    SecureSpecialistDTO save(SpecialistDTO specialistDTO);
}
