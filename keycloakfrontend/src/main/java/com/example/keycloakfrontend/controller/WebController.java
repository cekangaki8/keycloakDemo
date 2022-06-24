/* 
created by cekangaki 
created on 6/21/22 
inside the package - com.porstecth.learning.sampleclient 
*/

package com.example.keycloakfrontend.controller;

import com.example.keycloakfrontend.model.MailItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class WebController {


    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping(path = "/mailitems")
    public String mailitems(Principal principal, Model model) {
        List<MailItem> mailItems = addMailitems();
        model.addAttribute("mailitems", mailItems);
        model.addAttribute("username", principal.getName());
        return "mailitems";
    }

   /* @GetMapping(path = "/customersusingspringsecurity")
    public String customersusingspringsecurity(Principal principal, Model model) {
        addCustomers();
        Iterable<Customer> customers = customerDAO.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("username", principal.getName());
        return "customers";
    }*/

    // add customers for demonstration
    public List<MailItem> addMailitems() {

        return List.of(
                MailItem.builder().id(1L).name("Foo Industries").product("EXP").address("1111 foo blvd").build(),
                MailItem.builder().id(2L).name("Bar LLP").product("MAX").address("2222 bar street").build(),
                MailItem.builder().id(3L).name("Big LLC").product("GND").address("33 Main St").build()
        );
    }
}
