package edu.jsp.BankingApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.jsp.BankingApplication.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
   @Query(value ="select a from Account a where a.user.uid= ?")
   
   List<Account> getAccountByUserid(long userId);

}
