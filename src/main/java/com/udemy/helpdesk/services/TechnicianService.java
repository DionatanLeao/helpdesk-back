package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Technician;
import com.udemy.helpdesk.domain.dtos.TechnicianDTO;
import com.udemy.helpdesk.repository.TechnicianRepository;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {
    @Autowired
    private TechnicianRepository technicianRepository;

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician findById(Integer id) {
        return technicianRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Technician not found for id: " + id));
    }

    public Technician create(TechnicianDTO technicianDto) {
        technicianDto.setId(null);
        return technicianRepository.save(new Technician(technicianDto));
    }
}
