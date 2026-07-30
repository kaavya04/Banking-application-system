package edu.jsp.BankingApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.jsp.BankingApplication.entity.Account;
import edu.jsp.BankingApplication.entity.User;
import edu.jsp.BankingApplication.exception.ResourcesNotFoundException;
import edu.jsp.BankingApplication.repository.AccountRepository;
import edu.jsp.BankingApplication.repository.UserRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    // CREATE ACCOUNT
    public ResponseEntity<String> createAccount(long userId, Account account) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundException("User", "User Id=", userId));

        u.addAccount(account);
        userRepository.save(u);

        return new ResponseEntity<>("Account created", HttpStatus.CREATED);
    }

    // GET ACCOUNT BY ID
    public ResponseEntity<Account> getAccount(long accountId) {
        Account a = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourcesNotFoundException("Account", "AccountId=", accountId));

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    // DELETE ACCOUNT
    public ResponseEntity<String> deleteAccount(long userId, long accountId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundException("User", "User Id=", userId));

        Account a = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourcesNotFoundException("Account", "AccountId=", accountId));

        u.removeAccount(a);
        userRepository.save(u);

        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
    }

    // GET ACCOUNTS BY USER ID
    public ResponseEntity<List<Account>> getAccountByUserId(long userId) {
        List<Account> list = accountRepository.getAccountByUserid(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // GET BALANCE
    public ResponseEntity<Double> getBalance(long accountId) {
        Account a = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourcesNotFoundException("Account", "AccountId=", accountId));

        return new ResponseEntity<>(a.getBalance(), HttpStatus.OK);
    }
}