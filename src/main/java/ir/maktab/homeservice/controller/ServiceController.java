package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/11/2022 12:23 AM
 */


import ir.maktab.homeservice.model.Service;
import ir.maktab.homeservice.service.ServiceService;
import ir.maktab.homeservice.service.dto.ServiceDTO;
import ir.maktab.homeservice.service.dto.extra.ServiceAbstractDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/service")
public class ServiceController {

    private final ServiceService serviceService;


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<ServiceAbstractDTO> save(@RequestBody ServiceAbstractDTO serviceDTO) {
        return new ResponseEntity<>(serviceService.save(serviceDTO), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<ServiceAbstractDTO> update(@RequestBody ServiceAbstractDTO serviceDTO) {
        return new ResponseEntity<>(serviceService.save(serviceDTO), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<List<Service>> getAllServices() {
        return new ResponseEntity<>(serviceService.fetchAllServices(), HttpStatus.OK);
    }
}
