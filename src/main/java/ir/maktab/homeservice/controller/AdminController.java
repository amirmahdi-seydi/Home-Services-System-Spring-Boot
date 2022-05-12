package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/10/2022 2:24 AM
 */


import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.service.AdminService;
import ir.maktab.homeservice.service.CustomerService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.dto.AdminDTO;
import ir.maktab.homeservice.service.dto.CustomerDTO;
import ir.maktab.homeservice.service.dto.extra.SecureAdminDTO;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;
import ir.maktab.homeservice.service.dto.extra.SecureSpecialistDTO;
import ir.maktab.homeservice.service.dto.extra.request.ChangePasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService customerService;

    private final UserService userService;

    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<SecureAdminDTO> saveCustomer(@Valid @RequestBody AdminDTO adminDTO) {
        return new ResponseEntity<>(customerService.save(adminDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<SecureAdminDTO> updateCustomer(@Valid @RequestBody AdminDTO adminDTO) {
        return new ResponseEntity<>(customerService.save(adminDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        return new ResponseEntity<>(userService.changePassword(changePasswordDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/getAll")
    public ResponseEntity<List<SecureAdminDTO>> getAllCustomer() {
        return new ResponseEntity<>(customerService.fetchAllAdmins(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        try {
            customerService.deleteById(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        }
        catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


}
