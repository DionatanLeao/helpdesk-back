package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Client;
import com.udemy.helpdesk.domain.Technician;
import com.udemy.helpdesk.domain.Ticket;
import com.udemy.helpdesk.domain.enums.Priority;
import com.udemy.helpdesk.domain.enums.Profile;
import com.udemy.helpdesk.domain.enums.Status;
import com.udemy.helpdesk.repositories.PersonRepository;
import com.udemy.helpdesk.repositories.TechnicianRepository;
import com.udemy.helpdesk.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DbService {
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanceDb() {
        Technician tec1 = new Technician(null, "Tec 1", "532.429.590-60", "tec1@email.com", encoder.encode("12345678"));
        tec1.addProfiles(Profile.ADMIN);

        Client cli1 = new Client(null, "Cli 1", "415.042.640-61", "cli1@email.com", encoder.encode("12345678"));
        cli1.addProfiles(Profile.ADMIN);

        Ticket t1 = new Ticket(null, Priority.MEDIUM, Status.PROGRESS, "Ticket 01", "First Ticket", tec1, cli1);

        technicianRepository.save(tec1);
        personRepository.save(cli1);
        ticketRepository.save(t1);
    }
}
