package org.library.impl;

import org.library.model.Book;
import org.springframework.remoting.soap.SoapFaultException;
import org.springframework.security.core.context.SecurityContext;
import org.library.contract.BookManager;
import org.library.contract.LoanManager;
import org.library.model.Loan;
import org.library.model.Member;
import org.troparo.entities.loan.*;
import org.troparo.services.loanservice.BusinessException;
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
import org.apache.log4j.Logger;

@Named
public class LoanManagerImpl implements LoanManager {
    private static final Logger logger = Logger.getLogger(LoanManager.class.toString());
   /* private String token = "";
    private String login = "";
    private List<Loan> loanList = new ArrayList<>();*/

    @Inject
    BookManager bookManager;

    @Override
    public List<Loan> getLoansbyMember(SecurityContext context) {
        List<Loan> loanList = new ArrayList<>();
        String token = context.getAuthentication().getDetails().toString();
        String login = context.getAuthentication().getPrincipal().toString();
        logger.info("token: " + token);
        logger.info("login: " + login);

        try {
            LoanService loanService = new LoanService();
            GetLoanByCriteriasRequestType requestType = new GetLoanByCriteriasRequestType();
            requestType.setToken(token);
            LoanCriterias criterias = new LoanCriterias();
            criterias.setLogin(login.toUpperCase());
            logger.info("criterias passed: " + criterias.getLogin());
            requestType.setLoanCriterias(criterias);
            logger.info("token passed: " + requestType.getToken());
            GetLoanByCriteriasResponseType responseType = loanService.getLoanServicePort().getLoanByCriterias(requestType);
            List<LoanTypeOut> loanTypeOutList = responseType.getLoanListType().getLoanTypeOut();
            logger.info("checking if any results found. Size: " + loanTypeOutList.size());
            if (loanTypeOutList.size() != 0) {
                loanList = convertLoanListTypeOutIntoLoanList(loanTypeOutList, context);
            }
        } catch (SOAPFaultException e) {

            logger.error("Message: " + e.getMessage());
        } catch (BusinessExceptionLoan e) {
            logger.error(e.getFaultInfo().getErrorMessage().toString());
        }
        return loanList;
    }

    @Override
    public boolean renewLoan(Loan loan) {
        return false;
    }

    private List<Loan> convertLoanListTypeOutIntoLoanList(List<LoanTypeOut> loanTypeOutList, SecurityContext context) {
        List<Loan> loanList = new ArrayList<>();
        Member m = new Member();
        Book book = new Book();
        m.setLogin(context.getAuthentication().getPrincipal().toString());
        logger.info("trying to convert the list. Size: " + loanTypeOutList.size());
        for (LoanTypeOut typeOut : loanTypeOutList
        ) {
            Loan loan = new Loan();
            loan.setId(typeOut.getId());
            loan.setBorrower(m);
            loan.setBook(book);
            Date startDate = convertGregorianCalendarIntoDate(typeOut.getStartDate().toGregorianCalendar());
            loan.setStartDate(startDate);
            Date plannedEndDate = convertGregorianCalendarIntoDate(typeOut.getPlannedEndDate().toGregorianCalendar());
            loan.setPlannedEndDate(plannedEndDate);
            logger.info("trying to get book: " + typeOut.getBookId());
            book = bookManager.getBook(context, typeOut.getBookId());
            if (book != null) {
                loan.setBook(book);
            }
            loanList.add(loan);
        }
        logger.info("list converted: " + loanList.size());
        return loanList;
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
