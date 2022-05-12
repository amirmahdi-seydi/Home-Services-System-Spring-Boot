package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:03 AM
 */

import ir.maktab.homeservice.model.Admin;
import ir.maktab.homeservice.repository.base.UserBaseRepository;
import ir.maktab.homeservice.service.dto.extra.SecureAdminDTO;
import ir.maktab.homeservice.service.dto.extra.SecureSpecialistDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends UserBaseRepository<Admin> {

    @Query("select new ir.maktab.homeservice.service.dto.extra.SecureAdminDTO(" +
            "a.id, a.firstName, a.lastName, a.emailAddress," +
            " a.isActive, a.dateOfRegistration, a.userName, a.mobileNumber, a.userState, a.isSuperAdmin)" +
            " from Admin a order by a.id")
    List<SecureAdminDTO> fetchAllAdminsDTOS();
}
