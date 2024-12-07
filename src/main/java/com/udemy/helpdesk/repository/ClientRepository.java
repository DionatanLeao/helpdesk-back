package com.udemy.helpdesk.repository;

import com.udemy.helpdesk.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
