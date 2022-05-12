package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:05 AM
 */

import ir.maktab.homeservice.model.Specialist;
import ir.maktab.homeservice.repository.base.UserBaseRepository;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;
import ir.maktab.homeservice.service.dto.extra.SecureSpecialistDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SpecialistRepository extends UserBaseRepository<Specialist> {

    @Query("select new ir.maktab.homeservice.service.dto.extra.SecureSpecialistDTO(" +
            "s.id, s.firstName, s.lastName, s.emailAddress," +
            " s.isActive, s.dateOfRegistration,s.userName, s.mobileNumber," +
            " s.userState, s.score, s.profileImage)" +
            " from Specialist s order by s.id")
    List<SecureSpecialistDTO> fetchAllSpecialistDTOS();

    @Query("select s from Specialist s where s.id in :ids")
    List<Specialist> findSpecialistBy(List<Long> ids);
}
