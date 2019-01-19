package org.library.contract;


import org.library.model.Loan;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;

public interface LoanManager {

    List<Loan> getLoansbyMember(SecurityContext context);

    boolean renewLoan(Loan loan);


}
