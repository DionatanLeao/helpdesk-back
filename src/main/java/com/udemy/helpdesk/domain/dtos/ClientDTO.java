package com.udemy.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.helpdesk.domain.Client;
import com.udemy.helpdesk.domain.enums.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer id;
    @NotNull(message = "The NAME field is required")
    protected String name;
    @NotNull(message = "The CPF field is required")
    protected String cpf;
    @NotNull(message = "The EMAIL field is required")
    protected String email;
    @NotNull(message = "The PASSWORD field is required")
    protected String password;
    protected Set<Integer> profiles = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate creationDate = LocalDate.now();

    public ClientDTO() {
        super();
        addProfiles(Profile.CLIENT);
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.profiles = client.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.creationDate = client.getCreationDate();
        addProfiles(Profile.CLIENT);
    }

    public Set<Integer> getProfiles() {
        return this.profiles;
    }

    public void addProfiles(Profile profile) {
        this.profiles.add(profile.getCode());
    }
}
