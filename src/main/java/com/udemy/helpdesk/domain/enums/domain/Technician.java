package com.udemy.helpdesk.domain.enums.domain;

import com.udemy.helpdesk.domain.enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Technician extends Person {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "technician")
    private List<Ticket> tickets = new ArrayList<>();

    public Technician() {
        super();
        addProfiles(Profile.TECHNICIAN);
    }

    public Technician(Integer id, String name, String cpf, String email, String password, List<Ticket> tickets) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.TECHNICIAN);
    }
}
