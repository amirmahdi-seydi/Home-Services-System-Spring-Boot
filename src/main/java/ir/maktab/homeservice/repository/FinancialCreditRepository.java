package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:10 AM
 */

import ir.maktab.homeservice.model.FinancialCredit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FinancialCreditRepository extends JpaRepository<FinancialCredit, Long> {
}
