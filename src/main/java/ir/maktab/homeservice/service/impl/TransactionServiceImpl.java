package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:22 PM
 */

import ir.maktab.homeservice.model.Customer;
import ir.maktab.homeservice.model.Specialist;
import ir.maktab.homeservice.model.Transaction;
import ir.maktab.homeservice.repository.TransactionRepository;
import ir.maktab.homeservice.service.CustomerService;
import ir.maktab.homeservice.service.SpecialistService;
import ir.maktab.homeservice.service.TransactionService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;
import ir.maktab.homeservice.service.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Service
public class TransactionServiceImpl extends BaseServiceImpl<Transaction, Long, TransactionRepository>
        implements TransactionService {

    private final CustomerService customerService;

    private final SpecialistService specialistService;

    public TransactionServiceImpl(TransactionRepository repository, CustomerService customerService, SpecialistService specialistService) {
        super(repository);
        this.customerService = customerService;
        this.specialistService = specialistService;
    }

    @Override
    public Transaction save(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        Customer customer = customerService.findById(transactionDTO.getCustomerId()).get();
        Specialist specialist = specialistService.findById(transactionDTO.getSpecialistId()).get();

        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDateOfTransaction(Date.from(Instant.now()));
        transaction.setCustomer(customer);
        transaction.setSpecialist(specialist);

        return repository.save(transaction);
    }

    @Override
    public List<Transaction> fetchAllTransaction() {
        return repository.findAll();
    }
}
