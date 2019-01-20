package org.library.spring.controller;

import org.apache.log4j.Logger;
import org.library.contract.BookManager;
import org.library.contract.MemberManager;
import org.library.model.Book;
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
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {
    @Inject
    MemberManager memberManager;
    @Inject
    BookManager bookManager;

    private Logger logger = Logger.getLogger(UserController.class);

    @GetMapping("/")
    public String home() {

        return "home";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        logger.info("yuhuhuhuhu");
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/connect")
    public String user(Principal principal) {
        // Get authenticated user name from Principal
        logger.info("trying to get to user");
        logger.info(principal.getName());
        logger.info("role: " + principal.toString());

        logger.info("principal: " + principal);
        return "home";
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
        List<Book> books = new ArrayList<>();
        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        String token = context.getAuthentication().getDetails().toString();
        logger.info("token: "+token);
        logger.info(context.getAuthentication().getName());
        HashMap criterias = new HashMap<String, String>();
        criterias.put("ISBN", "12345678OK");
        criterias.put("TITLE", "");
        criterias.put("AUTHOR", "");
        books = bookManager.searchBooks(token, criterias);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("search");
        logger.info("going to search");
        /*logger.info("loan sample from member: "+m.getLoanList().get(0));*/
        return mv;
    }
}
