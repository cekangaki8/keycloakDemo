/* 
created by cekangaki 
created on 6/21/22 
inside the package - com.porstecth.learning.sampleclient 
*/

package com.example.keycloakfrontend.controller;

import com.example.keycloakfrontend.model.MailItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(path = "/mailitems")
    public String mailitems(Model model, @AuthenticationPrincipal OAuth2User principal, OAuth2AuthenticationToken authentication) {
        List<MailItem> mailItems = addMailitems();
        model.addAttribute("mailitems", mailItems);
        model.addAttribute("username", authentication.getPrincipal().getAttribute("preferred_username"));
        return "mailitems";
    }

    private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authentication) {
        return this.authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(), authentication.getName());
    }

    // add customers for demonstration
    public List<MailItem> addMailitems() {

        return List.of(
                MailItem.builder().id(1L).name("Foo Industries").product("EXP").address("1111 foo blvd").build(),
                MailItem.builder().id(2L).name("Bar LLP").product("MAX").address("2222 bar street").build(),
                MailItem.builder().id(3L).name("Big LLC").product("GND").address("33 Main St").build()
        );
    }
}
