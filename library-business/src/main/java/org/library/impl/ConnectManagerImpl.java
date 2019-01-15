package org.library.impl;

import org.library.contract.MemberManager;
import org.library.model.Member;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Named
public class ConnectManagerImpl implements AuthenticationProvider {

    @Inject
    MemberManager memberManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(authentication.getPrincipal());
        Collection<? extends GrantedAuthority> authorities = buildUserAuthority(memberManager.getMember(1));

        if (authentication.getPrincipal().toString().toUpperCase().equals("USER")) {
            Authentication auth = new UsernamePasswordAuthenticationToken("user",  "{noop}123", authorities);

            System.out.println("trucko: "+auth.getAuthorities());
            System.out.println("cred: "+auth.getCredentials());
            System.out.println("login: "+auth.getName());
            return auth;
        } else if (authentication.getPrincipal().toString().toUpperCase().equals("MOLGO")) {
            Authentication auth = new UsernamePasswordAuthenticationToken("loki", "{noop}458", authorities);
            return auth;
        } else {
            throw new
                    BadCredentialsException("External system authentication failed");

        }

    }


    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member m = memberManager.getMember(1);
        System.out.println("getting user details");
        List<GrantedAuthority> authorities = buildUserAuthority(m);
        return buildUserForAuthentication(m, authorities);
    }*/


    private User buildUser(){
        Member m = memberManager.getMember(1);
        Collection<GrantedAuthority> authorities = buildUserAuthority(m);
        System.out.println("building user");
        User user = new User(m.getLogin(), m.getToken(),authorities);
        return user;
    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(Member m,
                                            List<GrantedAuthority> authorities) {
        System.out.println("login: "+m.getLogin());
        System.out.println("pwd: "+m.getPassword());
        User user = new User(m.getLogin(), m.getPassword(), authorities);
        System.out.println("auh: "+authorities.get(0));
        return user;
    }



    private Collection<GrantedAuthority> buildUserAuthority(Member m) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        setAuths.add(new SimpleGrantedAuthority(m.getRole()));

        Collection<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        System.out.println("result: "+((ArrayList<GrantedAuthority>) result).get(0));

        return result;
    }



}