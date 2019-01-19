package org.library.impl;

import org.apache.log4j.Logger;
import org.library.contract.BookManager;
import org.library.contract.LoanManager;
import org.library.model.Book;
import org.library.model.Loan;
import org.library.model.Member;
import org.springframework.security.core.context.SecurityContext;
import org.troparo.entities.loan.GetLoanByCriteriasRequestType;
import org.troparo.entities.loan.GetLoanByCriteriasResponseType;
import org.troparo.entities.loan.LoanCriterias;
import org.troparo.entities.loan.LoanTypeOut;
import org.troparo.services.loanservice.BusinessExceptionLoan;
import org.troparo.services.loanservice.LoanService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Named
public class LoanManagerImpl implements LoanManager {
    private static final Logger logger = Logger.getLogger(LoanManager.class.toString());
   /* private String token = "";
    private String login = "";
    private List<Loan> loanList = new ArrayList<>();*/

    @Inject
    BookManager bookManager;



    @Override
    public boolean renewLoan(Loan loan) {
        return false;
    }



    protected Date convertGregorianCalendarIntoDate(GregorianCalendar gDate) {
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gDate);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        Date date = xmlCalendar.toGregorianCalendar().getTime();
        return date;

    }
}
