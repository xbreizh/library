package org.library.spring.controller;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;


import org.library.contract.MemberManager;
import org.library.model.Loan;
import org.library.model.Member;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

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
      logger.info("role: "+principal.toString());

      logger.info("principal: "+principal);
      return "index";
   }

   @GetMapping("/mySpace")
   public ModelAndView admin() {
      // Get authenticated user name from SecurityContext
      SecurityContext context = SecurityContextHolder.getContext();
      logger.info(context.getAuthentication().getName());
     /* logger.info(context.getAuthentication().getCredentials().toString());
      logger.info(context.getAuthentication().toString());*/
      Member m = memberManager.getMember(context);
      ModelAndView mv = new ModelAndView();
      mv.addObject(m);
      mv.setViewName("mySpace");
      logger.info("Token from member: "+m);
      /*logger.info("loan sample from member: "+m.getLoanList().get(0));*/
      return mv;
   }
}
