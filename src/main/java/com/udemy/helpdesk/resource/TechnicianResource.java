package com.udemy.helpdesk.resource;

import com.udemy.helpdesk.domain.Technician;
import com.udemy.helpdesk.domain.dtos.TechnicianDTO;
import com.udemy.helpdesk.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/technicians")
public class TechnicianResource {
    @Autowired
    private TechnicianService technicianService;

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        return ResponseEntity.ok()
                .body(technicianService.findAll().stream().map(TechnicianDTO::new)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(new TechnicianDTO(technicianService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@RequestBody TechnicianDTO technicianDto) {
        Technician technician = technicianService.create(technicianDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(technician.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable Integer id) {
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
