package com.udemy.helpdesk.domain.enums.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Client extends Person {
    private List<Ticket> tickets = new ArrayList<>();

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
    }
}
