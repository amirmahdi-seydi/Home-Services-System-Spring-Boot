package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:14 PM
 */

import ir.maktab.homeservice.model.FinansicalCredit;
import ir.maktab.homeservice.repository.FinancialCreditRepository;
import ir.maktab.homeservice.service.FinancialCreditService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;

public class FinancialCreditServiceServiceImpl extends BaseServiceImpl<FinansicalCredit, Long, FinancialCreditRepository>
        implements FinancialCreditService {

    public FinancialCreditServiceServiceImpl(FinancialCreditRepository repository) {
        super(repository);
    }
}
