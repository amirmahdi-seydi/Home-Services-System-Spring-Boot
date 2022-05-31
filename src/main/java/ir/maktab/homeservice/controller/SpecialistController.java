package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/8/2022 10:23 PM
 */


import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.service.SpecialistService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.dto.CustomerDTO;
import ir.maktab.homeservice.service.dto.ResetPasswordDTO;
import ir.maktab.homeservice.service.dto.SpecialistDTO;
import ir.maktab.homeservice.service.dto.SpecialistServiceDTO;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;
import ir.maktab.homeservice.service.dto.extra.SecureSpecialistDTO;
import ir.maktab.homeservice.service.dto.extra.request.ChangePasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/specialist")
public class SpecialistController {

    private final SpecialistService specialistService;

    private final UserService userService;


    public SpecialistController(SpecialistService specialistService, UserService userService) {
        this.specialistService = specialistService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('specialist')")
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<SecureSpecialistDTO> registerSpecialist(@Valid @RequestBody SpecialistDTO specialistDTO) {
        return new ResponseEntity<>(specialistService.save(specialistDTO), HttpStatus.OK);
    }


   @PreAuthorize("hasRole('specialist') or hasRole('admin')")
    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<SecureSpecialistDTO> updateSpecialist(@Valid @RequestBody SpecialistDTO specialistDTO) {
        return new ResponseEntity<>(specialistService.save(specialistDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('specialist')")
    @ResponseBody
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        return new ResponseEntity<>(userService.changePassword(changePasswordDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/getAll")
    public ResponseEntity<List<SecureSpecialistDTO>> getAllSpecialist() {
        return new ResponseEntity<>(specialistService.fetchAllSpecialist(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('specialist') or hasRole('admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSpecialist(@PathVariable String id) {
        try{
            specialistService.deleteById(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        }
        catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


}
