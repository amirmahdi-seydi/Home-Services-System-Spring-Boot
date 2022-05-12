package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:08 AM
 */

import ir.maktab.homeservice.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ServiceRepository extends JpaRepository<Service, Long> {

    Service findByName(String name);

    Boolean existsByName(String name);


}
