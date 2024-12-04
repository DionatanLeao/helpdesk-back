package com.udemy.helpdesk.domain.enums.domain;

import com.udemy.helpdesk.domain.enums.Priority;
import com.udemy.helpdesk.domain.enums.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Ticket {
    private Integer id;
    private LocalDate openingDate = LocalDate.now();
    private LocalDate closingDate;
    private Priority priority;
    private Status status;
    private String title;
    private String observations;
    private Technician technician;
    private Client client;

    public Ticket(Integer id,
                  Priority priority,
                  Status status,
                  String title,
                  String observations,
                  Technician technician,
                  Client client) {
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.title = title;
        this.observations = observations;
        this.technician = technician;
        this.client = client;
    }
}
