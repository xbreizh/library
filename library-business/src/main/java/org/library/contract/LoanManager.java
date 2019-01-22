package org.library.contract;


import org.library.model.Loan;

public interface LoanManager {


    boolean renewLoan(String token, Loan loan);

    boolean isRenewable(String token, int id);


}
