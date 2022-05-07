package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:10 PM
 */

import ir.maktab.homeservice.model.Admin;
import ir.maktab.homeservice.repository.AdminRepository;
import ir.maktab.homeservice.service.AdminService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;

public class AdminServiceImpl extends BaseServiceImpl<Admin, Long, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

}
