package org.library.impl;


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

import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Named
public class MemberManagerImpl implements MemberManager {

    private String token="";
    private String login="";
    private MemberTypeOut memberTypeOut;
    private Member member;


    @Override
    public Member getMember(SecurityContext context) {
            token = context.getAuthentication().getDetails().toString();
            login = context.getAuthentication().getPrincipal().toString();
        System.out.println("token: "+token);
        System.out.println("login: "+login);
        try {
            MemberService memberService = new MemberService();
            GetMemberByLoginRequestType requestType= new GetMemberByLoginRequestType();
            requestType.setToken(token);
            requestType.setLogin(login);
            /*memberService.getMemberServicePort().getMemberByLogin(requestType);*/
            GetMemberByLoginResponseType responseType = memberService.getMemberServicePort().getMemberByLogin(requestType);
            System.out.println("response: "+responseType.getMemberTypeOut().getEmail());
            memberTypeOut = responseType.getMemberTypeOut();

            // converting into Member
            member = convertMemberTypeOutIntoMember( memberTypeOut);
        }catch (NullPointerException e){
            System.out.println("Issue while trying to get member details");
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
        GregorianCalendar cal = memberTypeOut.getDateJoin().toGregorianCalendar();
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        Date date = xmlCalendar.toGregorianCalendar().getTime();
        member.setDateJoin(date);
        member.setRole(memberTypeOut.getRole());

        System.out.println("member from business: " +member);
        return member;
    }


}
