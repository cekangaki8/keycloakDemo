/* 
created by cekangaki 
created on 6/23/22 
inside the package - com.example.keycloakfrontend.model 
*/

package com.example.keycloakfrontend.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailItem {

    private Long id;

    private String address;

    private String name;

    private String product;
}
