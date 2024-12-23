package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Client;
import com.udemy.helpdesk.domain.Priority;
import com.udemy.helpdesk.domain.Technician;
import com.udemy.helpdesk.domain.Ticket;
import com.udemy.helpdesk.domain.enums.Profile;
import com.udemy.helpdesk.domain.enums.Status;
import com.udemy.helpdesk.repository.PersonRepository;
import com.udemy.helpdesk.repository.TechnicianRepository;
import com.udemy.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public void instanceDb() {
        Technician tec1 = new Technician(null, "Tec 1", "098.765.432-10", "tec1@email.com", "123");
        tec1.addProfiles(Profile.ADMIN);

        Client cli1 = new Client(null, "Cli 1", "123.456.789-01", "cli1@email", "123");

        Ticket t1 = new Ticket(null, Priority.MEDIUM, Status.PROGRESS, "Ticket 01", "First Ticket", tec1, cli1);

        technicianRepository.saveAll(List.of(tec1));
        personRepository.saveAll(List.of(cli1));
        ticketRepository.saveAll(List.of(t1));
    }
}
