package it.accenture.model.service;

import it.accenture.model.Person;
import it.accenture.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {

    private PersonRepo pr;

    @Autowired
    public PersonService(PersonRepo pr) {
        this.pr = pr;
    }

    public List<Person> findAll () {
        List<Person> listP = StreamSupport
                .stream(pr.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return listP;
    }
}
