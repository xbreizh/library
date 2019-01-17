package org.library.spring.controller;

import java.security.Principal;

import org.library.contract.BookManager;
import org.library.contract.MemberManager;
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


   @GetMapping("/")
   public String index() {

      return "index";
   }

   @GetMapping("/connect")
   public String user(Principal principal) {
      // Get authenticated user name from Principal
      System.out.println("trying to get to user");
      System.out.println(principal.getName());
      System.out.println("role: "+principal.toString());

      System.out.println("principal: "+principal);
      return "index";
   }

   @GetMapping("/mySpace")
   public ModelAndView admin() {
      // Get authenticated user name from SecurityContext
      SecurityContext context = SecurityContextHolder.getContext();
      System.out.println(context.getAuthentication().getName());
      System.out.println(context.getAuthentication().getCredentials());
      System.out.println(context.getAuthentication());
      Member m = memberManager.getMember(context);
      ModelAndView mv = new ModelAndView();
      mv.addObject(m);
      mv.setViewName("mySpace");
      System.out.println("Token from member: "+m);
      return mv;
   }
}
