package com.udemy.helpdesk.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDTO {
    private String email;
    private String password;
}
