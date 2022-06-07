package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/7/2022 4:26 PM
 */

import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.service.CustomerService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.dto.CustomerDTO;
import ir.maktab.homeservice.service.dto.OfferDTO;
import ir.maktab.homeservice.service.dto.ResetPasswordDTO;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;
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
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final UserService userService;


    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<SecureCustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.save(customerDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('customer') or hasRole('admin')")
    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<SecureCustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.save(customerDTO), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('customer')")
    @ResponseBody
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        return new ResponseEntity<>(userService.changePassword(changePasswordDTO), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('admin')")
    @GetMapping("/getAll")
    public ResponseEntity<List<SecureCustomerDTO>> getAllCustomer() {
        return new ResponseEntity<>(customerService.fetchAllCustomer(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('customer') or hasRole('admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        try{
            customerService.deleteById(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        }
        catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
