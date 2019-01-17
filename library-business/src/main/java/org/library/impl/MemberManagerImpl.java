package org.library.impl;


import org.library.contract.MemberManager;
import org.library.model.Member;
import org.springframework.security.core.context.SecurityContext;

import javax.inject.Named;

@Named
public class MemberManagerImpl implements MemberManager {


    @Override
    public String getMember(SecurityContext context) {
        try {
            String token = "context token? " + context.getAuthentication().getDetails();
            System.out.println(token);
        }catch (NullPointerException e){
            System.out.println("truc exception");
        }
        return "plok";
    }
}
