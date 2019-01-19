package org.library.spring.controller;

import org.library.contract.MemberManager;
import org.library.model.Loan;
import org.library.model.Member;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class UserController {
    @Inject
    MemberManager memberManager;

    private Logger logger = Logger.getLogger(this.getClass().getName());


    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/connect")
    public String user(Principal principal) {
        // Get authenticated user name from Principal
        logger.info("trying to get to user");
        logger.info(principal.getName());
        logger.info("role: " + principal.toString());

        logger.info("principal: " + principal);
        return "index";
    }

    @GetMapping("/mySpace")
    public ModelAndView mySpace() {
        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        String token = context.getAuthentication().getDetails().toString();
        String login = context.getAuthentication().getPrincipal().toString();
        logger.info("controller: "+context.getAuthentication().getName());
     /* logger.info(context.getAuthentication().getCredentials().toString());
      logger.info(context.getAuthentication().toString());*/
        Member m = memberManager.getMember(token, login);
        logger.info("Member retrieved: " + m);
       /* logger.info("Member retrieved: " + m.getLoanList().size());*/
        ModelAndView mv = new ModelAndView();
        List<Loan> loanList = new ArrayList<>();

        logger.info("loanlist: "+loanList);
        if(loanList.size() > 0) {
            logger.info("loanList > 0");
            loanList =  m.getLoanList();
        }
        mv.addObject("loanList", loanList);
        mv.addObject("member",m);
        mv.setViewName("mySpace");
        /*logger.info("loan sample from member: "+m.getLoanList().get(0));*/
        return mv;
    }

    @GetMapping("/search")
    public ModelAndView search() {
        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        logger.info(context.getAuthentication().getName());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("search");
        logger.info("going to search");
        /*logger.info("loan sample from member: "+m.getLoanList().get(0));*/
        return mv;
    }
}
