package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Person;
import com.udemy.helpdesk.domain.Technician;
import com.udemy.helpdesk.domain.dtos.TechnicianDTO;
import com.udemy.helpdesk.repositories.PersonRepository;
import com.udemy.helpdesk.repositories.TechnicianRepository;
import com.udemy.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician findById(Integer id) {
        return technicianRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Technician not found for id: " + id));
    }

    public Technician create(TechnicianDTO technicianDto) {
        technicianDto.setId(null);
        technicianDto.setPassword(encoder.encode(technicianDto.getPassword()));
        validByCpfAndEmail(technicianDto);
        return technicianRepository.save(new Technician(technicianDto));
    }

    public Technician update(Integer id, TechnicianDTO technicianDTO) {
        technicianDTO.setId(id);
        validByCpfAndEmail(technicianDTO);
        return technicianRepository.save(new Technician(technicianDTO));
    }

    public void delete(Integer id) {
        Technician technician = findById(id);
        if (technician.getTickets().size() > 0) {
            throw new DataIntegrityViolationException("Technician has work orders and cannot be deleted!");
        }
        technicianRepository.deleteById(id);
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
