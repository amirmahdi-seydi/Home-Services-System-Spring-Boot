package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:34 AM
 */

import ir.maktab.homeservice.model.Admin;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.AdminDTO;
import ir.maktab.homeservice.service.dto.extra.SecureAdminDTO;

import java.util.List;

public interface AdminService extends BaseService<Admin, Long> {

    SecureAdminDTO save(AdminDTO adminDTO);

    List<SecureAdminDTO> fetchAllAdmins();
}
