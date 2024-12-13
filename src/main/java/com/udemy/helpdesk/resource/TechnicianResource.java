package com.udemy.helpdesk.resource;

import com.udemy.helpdesk.domain.dtos.TechnicianDTO;
import com.udemy.helpdesk.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/technicians")
public class TechnicianResource {
    @Autowired
    private TechnicianService technicianService;

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new TechnicianDTO(technicianService.findById(id)));
    }

}
