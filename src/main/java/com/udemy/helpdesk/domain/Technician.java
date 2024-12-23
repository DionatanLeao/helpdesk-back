package com.udemy.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udemy.helpdesk.domain.dtos.TechnicianDTO;
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
public class Technician extends Person {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Ticket> tickets = new ArrayList<>();

    public Technician() {
        super();
        addProfiles(Profile.TECHNICIAN);
    }

    public Technician(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.TECHNICIAN);
    }

    public Technician(TechnicianDTO technicianDto) {
        this.id = technicianDto.getId();
        this.name = technicianDto.getName();
        this.cpf = technicianDto.getCpf();
        this.email = technicianDto.getEmail();
        this.password = technicianDto.getPassword();
        this.profiles = technicianDto.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.creationDate = technicianDto.getCreationDate();
    }
}
