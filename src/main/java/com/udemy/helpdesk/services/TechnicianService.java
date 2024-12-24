package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Person;
import com.udemy.helpdesk.domain.Technician;
import com.udemy.helpdesk.domain.dtos.TechnicianDTO;
import com.udemy.helpdesk.repository.PersonRepository;
import com.udemy.helpdesk.repository.TechnicianRepository;
import com.udemy.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TechnicianService {
    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician findById(Integer id) {
        return technicianRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Technician not found for id: " + id));
    }

    public Technician create(TechnicianDTO technicianDto) {
        technicianDto.setId(null);
        validByCpfAndEmail(technicianDto);
        return technicianRepository.save(new Technician(technicianDto));
    }

    private void validByCpfAndEmail(TechnicianDTO technicianDto) {
        Optional<Person> validate = personRepository.findByCpf(technicianDto.getCpf());
        if (validate.isPresent() && !Objects.equals(validate.get().getId(), technicianDto.getId())) {
            throw new DataIntegrityViolationException("CPF already registered in the system");
        }
        validate = personRepository.findByEmail(technicianDto.getEmail());
        if (validate.isPresent() && !Objects.equals(validate.get().getId(), technicianDto.getId())) {
            throw new DataIntegrityViolationException("E-mail already registered in the system");
        }
    }
}
