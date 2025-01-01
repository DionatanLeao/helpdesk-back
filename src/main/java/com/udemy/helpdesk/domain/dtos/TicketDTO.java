package com.udemy.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.helpdesk.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;
    private Integer priority;
    private Integer status;
    private String title;
    private String observations;
    private Integer technician;
    private Integer client;
    private String technicianName;
    private String clientName;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.openingDate = ticket.getOpeningDate();
        this.closingDate = ticket.getClosingDate();
        this.priority = ticket.getPriority().getCode();
        this.status = ticket.getStatus().getCode();
        this.title = ticket.getTitle();
        this.observations = ticket.getObservations();
        this.technician = ticket.getTechnician().getId();
        this.client = ticket.getClient().getId();
        this.technicianName = ticket.getTechnician().getName();
        this.clientName = ticket.getClient().getName();
    }
}
