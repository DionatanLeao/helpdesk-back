package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Client;
import com.udemy.helpdesk.domain.Technician;
import com.udemy.helpdesk.domain.Ticket;
import com.udemy.helpdesk.domain.dtos.TicketDTO;
import com.udemy.helpdesk.domain.enums.Priority;
import com.udemy.helpdesk.domain.enums.Status;
import com.udemy.helpdesk.repository.TicketRepository;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private ClientService clientService;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Integer id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.orElseThrow(() -> new ObjectNotFoundException("Client not found for id: " + id));
    }

    public Ticket create(TicketDTO ticketDto) {
        return ticketRepository.save(newTicket(ticketDto));
    }

    public Ticket update(Integer id, TicketDTO ticketDto) {
        ticketDto.setId(id);
        Ticket updateTicket = findById(id);
        updateTicket = newTicket(ticketDto);
        return ticketRepository.save(updateTicket);
    }

    private Ticket newTicket(TicketDTO ticketDto) {
        Technician technician = technicianService.findById(ticketDto.getTechnician());
        Client client = clientService.findById(ticketDto.getClient());
        return buildTicket(ticketDto, technician, client);
    }

    private static Ticket buildTicket(TicketDTO ticketDto, Technician technician, Client client) {
        Ticket ticket = new Ticket();
        validatedIsUpdate(ticketDto, ticket);
        validatedClosingDate(ticketDto, ticket);
        ticket.setTechnician(technician);
        ticket.setClient(client);
        ticket.setPriority(Priority.toEnum(ticketDto.getPriority()));
        ticket.setStatus(Status.toEnum(ticketDto.getStatus()));
        ticket.setTitle(ticketDto.getTitle());
        ticket.setObservations(ticketDto.getObservations());
        return ticket;
    }

    private static void validatedIsUpdate(TicketDTO ticketDto, Ticket ticket) {
        if (!Objects.isNull(ticketDto.getId())) {
            ticket.setId(ticketDto.getId());
        }
    }

    private static void validatedClosingDate(TicketDTO ticketDto, Ticket ticket) {
        if (ticketDto.getStatus().equals(2)) {
            ticket.setClosingDate(LocalDate.now());
        }
    }
}
