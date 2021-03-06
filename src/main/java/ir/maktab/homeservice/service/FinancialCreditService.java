package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:37 AM
 */

import ir.maktab.homeservice.model.FinancialCredit;
import ir.maktab.homeservice.service.base.BaseService;

import java.math.BigDecimal;


public interface FinancialCreditService extends BaseService<FinancialCredit, Long> {

    FinancialCredit rechargeFinancialCredit(BigDecimal amount);



}
