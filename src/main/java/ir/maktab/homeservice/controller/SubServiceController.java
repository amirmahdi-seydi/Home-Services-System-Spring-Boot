package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/11/2022 2:50 AM
 */


import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.model.SubService;
import ir.maktab.homeservice.service.SubServiceService;
import ir.maktab.homeservice.service.dto.SubServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/sub-service")
public class SubServiceController {

    private final SubServiceService subServiceService;


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<SubService> save(@RequestBody SubService subService) {
        return new ResponseEntity<>(subServiceService.save(subService), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<SubService> update(@RequestBody SubService subService) {
        return new ResponseEntity<>(subServiceService.save(subService), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<List<SubServiceDTO>> getAll() {
        return new ResponseEntity<>(subServiceService.fetchAllSubServiceNames(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        try{
            subServiceService.deleteById(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        }
        catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
