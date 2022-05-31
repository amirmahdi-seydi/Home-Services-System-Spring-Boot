package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:06 AM
 */

import ir.maktab.homeservice.model.SubService;
import ir.maktab.homeservice.service.dto.SubServiceDTO;
import ir.maktab.homeservice.service.dto.extra.SecureSpecialistDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubServiceRepository extends JpaRepository<SubService, Long> {

    Boolean existsByName(String name);

    SubService findSubServiceByName(String name);

    @Query("select new ir.maktab.homeservice.service.dto.SubServiceDTO(" +
            "s.id, s.name )" +
            " from SubService s order by s.id")
    List<SubServiceDTO> fetchAllSubServiceNames();
}

