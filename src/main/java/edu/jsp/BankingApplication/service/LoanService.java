package edu.jsp.BankingApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jsp.BankingApplication.entity.Loan;
import edu.jsp.BankingApplication.entity.User;
import edu.jsp.BankingApplication.exception.ResourcesNotFoundException;
import edu.jsp.BankingApplication.repository.LoanRepository;
import edu.jsp.BankingApplication.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class LoanService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LoanRepository loanRepository;

    // APPLY LOAN
    @Transactional
    public String applyLoan(long userId, Loan loan) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundException("User", "User Id=", userId));

        u.addLoan(loan);
        userRepository.save(u);

        return "Loan applied";
    }

    // GET LOAN BY ID
    public Loan getLoanById(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourcesNotFoundException("Loan", "Loan Id=", loanId));
    }

    // DELETE LOAN
    public String deleteLoan(Long userId, Long loanId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundException("User", "User Id=", userId));

        Loan l = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourcesNotFoundException("Loan", "Loan Id=", loanId));

        u.removeLoan(l);
        userRepository.save(u);   // IMPORTANT
        loanRepository.delete(l);

        return "Loan deleted";
    }

    // GET LOANS BY USER ID
    public List<Loan> getLoanByUserId(long userId) {
        return loanRepository.findByUserId(userId);
    }

    // GET LOAN STATUS
    public String getLoanStatus(long loanId) {
        Loan l = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourcesNotFoundException("Loan", "Loan Id=", loanId));

        return l.getStatus();
    }
}