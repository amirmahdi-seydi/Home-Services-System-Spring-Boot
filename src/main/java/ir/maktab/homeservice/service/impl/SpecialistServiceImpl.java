package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:13 PM
 */

import ir.maktab.homeservice.model.Specialist;
import ir.maktab.homeservice.repository.SpecialistRepository;
import ir.maktab.homeservice.service.SpecialistService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;

public class SpecialistServiceImpl extends BaseServiceImpl<Specialist, Long, SpecialistRepository>
        implements SpecialistService {

    public SpecialistServiceImpl(SpecialistRepository repository) {
        super(repository);
    }
}
