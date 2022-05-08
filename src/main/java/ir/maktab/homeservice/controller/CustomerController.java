package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/7/2022 4:26 PM
 */

import ir.maktab.homeservice.exception.NotFoundException;
import ir.maktab.homeservice.service.CustomerService;
import ir.maktab.homeservice.service.dto.CustomerDTO;
import ir.maktab.homeservice.service.dto.extra.SecureCustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<SecureCustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.save(customerDTO), HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<SecureCustomerDTO> updateCustomer(@RequestBody SecureCustomerDTO secureCustomerDTO) {
        return new ResponseEntity<>(customerService.update(secureCustomerDTO), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SecureCustomerDTO>> getAllCustomer() {
        return new ResponseEntity<>(customerService.fetchAllCustomer(), HttpStatus.OK);
    }

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
