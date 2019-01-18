package org.library.impl;


import org.library.contract.BookManager;
import org.library.contract.LoanManager;
import org.library.contract.MemberManager;
import org.library.model.Loan;
import org.library.model.Member;
import org.springframework.security.core.context.SecurityContext;
import org.troparo.entities.loan.*;
import org.troparo.entities.member.*;
import org.troparo.services.bookservice.BookService;
import org.troparo.services.loanservice.BusinessException;
import org.troparo.services.loanservice.LoanService;
import org.troparo.services.memberservice.BusinessExceptionMember;
import org.troparo.services.memberservice.MemberService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;

@Named
public class MemberManagerImpl implements MemberManager {
    private static final Logger logger = Logger.getLogger(MemberManagerImpl.class);

    private String token="";
    private String login="";
    private MemberTypeOut memberTypeOut;
    private Member member;

    @Inject
    LoanManager loanManager;
    @Inject
    BookManager bookManager;


    @Override
    public Member getMember(SecurityContext context) {
        token = context.getAuthentication().getDetails().toString();
        login = context.getAuthentication().getPrincipal().toString();
        logger.info("token: "+token);
        logger.info("login: "+login);
        try {
            MemberService memberService = new MemberService();
            GetMemberByLoginRequestType requestType= new GetMemberByLoginRequestType();
            requestType.setToken(token);
            requestType.setLogin(login);
            /*memberService.getMemberServicePort().getMemberByLogin(requestType);*/
            GetMemberByLoginResponseType responseType = memberService.getMemberServicePort().getMemberByLogin(requestType);
            logger.info("response: "+responseType.getMemberTypeOut().getEmail());
            memberTypeOut = responseType.getMemberTypeOut();

            // converting into Member
            member = convertMemberTypeOutIntoMember( memberTypeOut);
            logger.info("trying to pass loan to member");
            List<Loan> loanList = loanManager.getLoansbyMember(context);
            member.setLoanList(loanList);
            logger.info("member loan size: "+member.getLoanList().size());
            logger.info("loan list for that member: "+memberTypeOut.getLoanListType().getLoanTypeOut().get(0).getBookTypeOut().getTitle());
            /*// getting Loan list
            try {
                logger.info("trying to pass the loan list");

            }catch (NullPointerException e){
                logger.info("no loan so far for that user: "+member.getLogin());
                member.setLoanList(new ArrayList<Loan>());
            }*/
        }catch (NullPointerException e){
            logger.info("Issue while trying to get member details");
        } catch (BusinessExceptionMember businessExceptionMember) {
            businessExceptionMember.printStackTrace();
        }
        return member;
    }

    private Member convertMemberTypeOutIntoMember(MemberTypeOut memberTypeOut){
        Member member = new Member();
        member.setFirstName(memberTypeOut.getFirstName());
        member.setLastName(memberTypeOut.getLastName());
        member.setLogin(memberTypeOut.getLogin());
        member.setEmail(memberTypeOut.getEmail());
        // converting xml date into Date
        Date date = convertGregorianCalendarIntoDate(memberTypeOut.getDateJoin().toGregorianCalendar());
        member.setDateJoin(date);
        member.setRole(memberTypeOut.getRole());

        logger.info("member from business: " +member);
        return member;
    }

    protected Date convertGregorianCalendarIntoDate(GregorianCalendar gDate){
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
