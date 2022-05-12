package ir.maktab.homeservice.controller;
/*
 * created by Amir mahdi seydi 5/12/2022 12:46 AM
 */


import ir.maktab.homeservice.model.FinancialCredit;
import ir.maktab.homeservice.service.FinancialCreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/credit")
public class FinancialCreditController {

    private final FinancialCreditService financialCreditService;

    public FinancialCreditController(FinancialCreditService financialCreditService) {
        this.financialCreditService = financialCreditService;
    }


    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<List<FinancialCredit>> fetchAllFinancialCredit() {
        return new ResponseEntity<>(financialCreditService.findAll(), HttpStatus.OK);
    }


}
