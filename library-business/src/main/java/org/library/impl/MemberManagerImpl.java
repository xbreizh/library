package org.library.impl;

import org.library.contract.BookManager;
import org.library.contract.MemberManager;
import org.library.model.Member;

import javax.inject.Named;

@Named
public class MemberManagerImpl implements MemberManager {


    @Override
    public Member getMember(int id) {
        Member m = new Member();
        m.setLogin("Conor");
        m.setPassword("123");
        m.setId(1);
        m.setRole("USER");
        return m;
    }
}
