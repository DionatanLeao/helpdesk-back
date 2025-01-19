package com.udemy.helpdesk.resources;

import com.udemy.helpdesk.domain.Technician;
import com.udemy.helpdesk.domain.dtos.TechnicianDTO;
import com.udemy.helpdesk.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@RequestBody @Valid TechnicianDTO technicianDto) {
        Technician technician = technicianService.create(technicianDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(technician.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO technicianDTO) {
        return ResponseEntity.ok().body(new TechnicianDTO(technicianService.update(id, technicianDTO)));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable Integer id) {
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
