package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Client;
import com.udemy.helpdesk.domain.Person;
import com.udemy.helpdesk.domain.dtos.ClientDTO;
import com.udemy.helpdesk.repository.ClientRepository;
import com.udemy.helpdesk.repository.PersonRepository;
import com.udemy.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found for id: " + id));
    }

    public Client create(ClientDTO clientDto) {
        clientDto.setId(null);
        validByCpfAndEmail(clientDto);
        return clientRepository.save(new Client(clientDto));
    }

    public Client update(Integer id, ClientDTO clientDto) {
        clientDto.setId(id);
        Client updateClient = findById(id);
        validByCpfAndEmail(clientDto);
        updateClient = new Client(clientDto);
        return clientRepository.save(updateClient);
    }

    public void delete(Integer id) {
        Client client = findById(id);
        if (client.getTickets().size() > 0) {
            throw new DataIntegrityViolationException("Client has work orders and cannot be deleted!");
        }
        clientRepository.deleteById(id);
    }

    private void validByCpfAndEmail(ClientDTO clientDto) {
        Optional<Person> validate = personRepository.findByCpf(clientDto.getCpf());
        if (validate.isPresent() && !Objects.equals(validate.get().getId(), clientDto.getId())) {
            throw new DataIntegrityViolationException("CPF already registered in the system");
        }
        validate = personRepository.findByEmail(clientDto.getEmail());
        if (validate.isPresent() && !Objects.equals(validate.get().getId(), clientDto.getId())) {
            throw new DataIntegrityViolationException("E-mail already registered in the system");
        }
    }
}
