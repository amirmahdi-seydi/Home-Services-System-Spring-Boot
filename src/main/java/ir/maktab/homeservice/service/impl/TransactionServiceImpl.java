package ir.maktab.homeservice.service.impl;
/*
 * created by Amir mahdi seydi 5/7/2022 12:22 PM
 */

import ir.maktab.homeservice.model.Transaction;
import ir.maktab.homeservice.repository.TransactionRepository;
import ir.maktab.homeservice.service.TransactionService;
import ir.maktab.homeservice.service.base.BaseServiceImpl;

public class TransactionServiceImpl extends BaseServiceImpl<Transaction, Long, TransactionRepository>
        implements TransactionService {

    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }
}
