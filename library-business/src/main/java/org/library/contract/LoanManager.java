package org.library.contract;


import org.library.model.Loan;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;

public interface LoanManager {



    boolean renewLoan(Loan loan);


}
