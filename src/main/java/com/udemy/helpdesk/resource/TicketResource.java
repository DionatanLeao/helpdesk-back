package com.udemy.helpdesk.resource;

import com.udemy.helpdesk.domain.Ticket;
import com.udemy.helpdesk.domain.dtos.TicketDTO;
import com.udemy.helpdesk.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketResource {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> findAll() {
        return ResponseEntity.ok()
                .body(ticketService.findAll().stream().map(TicketDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new TicketDTO(ticketService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<TicketDTO> create(@RequestBody @Valid TicketDTO ticketDto) {
        Ticket ticket = ticketService.create(ticketDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(ticket.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
