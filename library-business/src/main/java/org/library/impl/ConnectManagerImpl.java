package org.library.impl;

import org.library.contract.MemberManager;
import org.library.model.Member;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.troparo.entities.connect.GetTokenRequestType;
import org.troparo.entities.connect.GetTokenResponseType;
import org.troparo.services.connectservice.BusinessException;
import org.troparo.services.connectservice.ConnectService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Named
public class ConnectManagerImpl implements AuthenticationProvider {

    @Inject
    MemberManager memberManager;

    private String token;
    private final String role = "USER";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(authentication.getPrincipal());
        ConnectService cs = new ConnectService();
        GetTokenRequestType t = new GetTokenRequestType();
        String login = authentication.getName().toUpperCase();
        String password = (String) authentication.getCredentials();
        t.setLogin(login);
        t.setPassword(password);
        System.out.println("login: "+login+" \n passwordd: "+password);
        try {
            GetTokenResponseType responseType = cs.getConnectServicePort().getToken(t);
            token = responseType.getReturn();
        } catch (BusinessException e) {
            e.printStackTrace();
            System.out.println("issue while trying to get the token");

        }
        System.out.println("token found: "+token);


        if (token!=null) {
            Authentication auth = new UsernamePasswordAuthenticationToken(login,  token, buildUserAuthority());
            System.out.println("trucko: "+auth.getAuthorities());
            System.out.println("cred: "+auth.getCredentials());
            System.out.println("login: "+auth.getName());
            String token = "abc123";
            ((UsernamePasswordAuthenticationToken) auth).setDetails(token);
            return auth;
        }  else {
            throw new
                    BadCredentialsException("External system authentication failed");

        }

    }


    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }





    private Collection<GrantedAuthority> buildUserAuthority() {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        setAuths.add(new SimpleGrantedAuthority(role));

        Collection<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        System.out.println("result: "+((ArrayList<GrantedAuthority>) result).get(0));

        return result;
    }



}
