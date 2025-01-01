package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Ticket;
import com.udemy.helpdesk.repository.TicketRepository;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket findById(Integer id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.orElseThrow(() -> new ObjectNotFoundException("Client not found for id: " + id));
    }
}
