package com.udemy.helpdesk.domain.enums.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Technician extends Person {
    private List<Ticket> tickets = new ArrayList<>();

    public Technician(Integer id, String name, String cpf, String email, String password, List<Ticket> tickets) {
        super(id, name, cpf, email, password);
    }
}
