package com.udemy.helpdesk;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Technician tec1 = new Technician(null, "Tec 1", "09876543210", "tec1@email.com", "123");
        tec1.addProfiles(Profile.ADMIN);

        Client cli1 = new Client(null, "Cli 1", "12345678901", "cli1@email", "123");

        Ticket t1 = new Ticket(null, Priority.MEDIUM, Status.PROGRESS, "Ticket 01", "First Ticket", tec1, cli1);

		technicianRepository.saveAll(Arrays.asList(tec1));
		personRepository.saveAll(Arrays.asList(cli1));
		ticketRepository.saveAll(Arrays.asList(t1));
    }
}
