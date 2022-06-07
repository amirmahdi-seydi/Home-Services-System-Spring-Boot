package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:08 AM
 */

import ir.maktab.homeservice.model.Service;
import ir.maktab.homeservice.model.Specialist;
import ir.maktab.homeservice.service.dto.ServiceDTO;
import ir.maktab.homeservice.service.dto.extra.SecureOfferDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ServiceRepository extends JpaRepository<Service, Long> {

    Service findByName(String name);

    Boolean existsByName(String name);

    @Query("select new ir.maktab.homeservice.service.dto.ServiceDTO(" +
            " s.id, s.name, s.description," +
            " s.basePrice, s.optionalDescription, s.subService.name)" +
            " from Service s order by s.id")
    List<ServiceDTO> fetchAllServicesDTO();

    @Query("select s from Service s join s.serviceSpecialist sp where sp.specialist.id = :id")
    List<Service> findSpecialistServices(@Param("id") Long id);

    @Query("select s from Service s where s.id in :ids")
    List<Service> findServiceBy(List<Long> ids);


    @Query("select s from Service s join Specialist sp where sp.speciality.name = s.subService.name and sp.id = :id")
    List<Service> findServicesBySpecialistSkill(@Param("id") Long id);


}
