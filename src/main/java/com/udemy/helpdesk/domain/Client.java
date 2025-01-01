package com.udemy.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udemy.helpdesk.domain.dtos.ClientDTO;
import com.udemy.helpdesk.domain.enums.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Client extends Person {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets = new ArrayList<>();

    public Client() {
        super();
        addProfiles(Profile.CLIENT);
    }

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.CLIENT);
    }

    public Client(ClientDTO clientDto) {
        this.id = clientDto.getId();
        this.name = clientDto.getName();
        this.cpf = clientDto.getCpf();
        this.email = clientDto.getEmail();
        this.password = clientDto.getPassword();
        this.profiles = clientDto.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.creationDate = clientDto.getCreationDate();
    }
}
