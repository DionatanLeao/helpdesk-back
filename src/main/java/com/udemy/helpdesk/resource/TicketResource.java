package com.udemy.helpdesk.resource;

import com.udemy.helpdesk.domain.dtos.TicketDTO;
import com.udemy.helpdesk.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketResource {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new TicketDTO(ticketService.findById(id)));
    }
}
