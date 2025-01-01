package com.udemy.helpdesk.resource;

import com.udemy.helpdesk.domain.dtos.TicketDTO;
import com.udemy.helpdesk.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
