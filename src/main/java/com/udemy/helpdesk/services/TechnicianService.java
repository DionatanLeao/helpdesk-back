package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Technician;
import com.udemy.helpdesk.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {
    @Autowired
    private TechnicianRepository technicianRepository;

    public Technician findById(Integer id) {
        return technicianRepository.findById(id).orElseThrow(null);
    }
}
