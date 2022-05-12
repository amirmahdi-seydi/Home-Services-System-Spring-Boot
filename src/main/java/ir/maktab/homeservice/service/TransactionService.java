package ir.maktab.homeservice.service;
/*
 * created by Amir mahdi seydi 5/7/2022 11:39 AM
 */

import ir.maktab.homeservice.model.Transaction;
import ir.maktab.homeservice.service.base.BaseService;
import ir.maktab.homeservice.service.dto.TransactionDTO;

import java.util.List;


public interface TransactionService extends BaseService<Transaction,Long> {

    Transaction save(TransactionDTO transactionDTO);

    List<Transaction> fetchAllTransaction();
}
