package com.udemy.helpdesk.services;

import com.udemy.helpdesk.domain.Person;
import com.udemy.helpdesk.repositories.PersonRepository;
import com.udemy.helpdesk.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> user = personRepository.findByEmail(email);
        if (user.isPresent()) {
            return new UserSpringSecurity(user.get().getId(),
                    user.get().getEmail(),
                    user.get().getPassword(),
                    user.get().getProfiles()    );
        }
        throw new UsernameNotFoundException(email);
    }
}
