package ir.maktab.homeservice.repository;
/*
 * created by Amir mahdi seydi 5/7/2022 11:10 AM
 */

import ir.maktab.homeservice.model.FinansicalCredit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FinancialCreditRepository extends JpaRepository<FinansicalCredit, Long> {
}
