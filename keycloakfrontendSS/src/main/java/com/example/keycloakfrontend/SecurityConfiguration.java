/* 
created by cekangaki 
created on 6/24/22 
inside the package - com.example.keycloakfrontend 
*/

package com.example.keycloakfrontend;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/mailitems*", "/users*")
                .hasRole("user")
                .anyRequest()
                .permitAll();
    }

}
