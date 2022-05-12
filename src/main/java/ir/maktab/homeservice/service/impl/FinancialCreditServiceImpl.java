package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:14 PM
 */

import ir.maktab.homeservice.config.security.CustomUserDetails;
import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.model.FinancialCredit;
import ir.maktab.homeservice.model.User;
import ir.maktab.homeservice.model.UserFactory;
import ir.maktab.homeservice.repository.FinancialCreditRepository;
import ir.maktab.homeservice.service.FinancialCreditService;
import ir.maktab.homeservice.service.UserService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class FinancialCreditServiceImpl extends BaseServiceImpl<FinancialCredit, Long, FinancialCreditRepository>
        implements FinancialCreditService {

    private final UserService userService;

    public FinancialCreditServiceImpl(FinancialCreditRepository repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    @Override
    public FinancialCredit rechargeFinancialCredit(BigDecimal amount) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        if (user instanceof Customer) {
            FinancialCredit financialCredit = new FinancialCredit();
            Customer customer = (Customer) user;
            financialCredit.setId(customer.getFinancialCredit().getId());
            financialCredit.setBalance(customer.getFinancialCredit().getBalance().add(amount));
            financialCredit = repository.save(financialCredit);

            customer.setFinancialCredit(financialCredit);
            userService.save(customer);

            return financialCredit;
        }

        return null;
    }
}
