package org.library.impl;

import org.apache.log4j.Logger;
import org.library.contract.BookManager;
import org.library.contract.LoanManager;
import org.library.model.Loan;
import org.troparo.entities.loan.IsRenewableRequestType;
import org.troparo.entities.loan.IsRenewableResponseType;
import org.troparo.services.loanservice.BusinessExceptionLoan;
import org.troparo.services.loanservice.LoanService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Named
public class LoanManagerImpl implements LoanManager {
    private static final Logger logger = Logger.getLogger(LoanManager.class.toString());
   /* private String token = "";
    private String login = "";
    private List<Loan> loanList = new ArrayList<>();*/

    @Inject
    BookManager bookManager;



    @Override
    public boolean renewLoan(String token, Loan loan) {
        return false;
    }

    @Override
    public boolean isRenewable(String token, int id) {
        LoanService loanService = new LoanService();
        IsRenewableRequestType requestType = new IsRenewableRequestType();
        requestType.setToken(token);
        requestType.setId(id);
        try {
            IsRenewableResponseType responseType = loanService.getLoanServicePort().isRenewable(requestType);
            return responseType.isReturn();

        } catch (BusinessExceptionLoan businessExceptionLoan) {
            logger.error(businessExceptionLoan.getMessage());
        }
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
