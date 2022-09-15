package it.accenture.service;

import it.accenture.exception.EntityNotFoundException;
import it.accenture.model.Person;
import it.accenture.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {
    public static final String ERROR_NOT_FOUND = "L'entità %s con id %d non esiste";
    private PersonRepo pr;

    @Autowired
    public PersonService(PersonRepo pr) {
        this.pr = pr;
    }

    public Iterable<Person> findAll () {
        return pr.findAll();
    }

    public Person saveOrUpdate(Person p) throws EntityNotFoundException {
        if (p.getId()!=null && !p.getId().equals(0) && !pr.existsById(p.getId())) {
            throw new EntityNotFoundException(ERROR_NOT_FOUND, Person.class, p.getId());
        }
        return pr.save(p);
    }

    public void deleteById(Integer id) throws EntityNotFoundException {
        if (pr.existsById(id)) {
            pr.deleteById(id);
        } else {
            throw new EntityNotFoundException(ERROR_NOT_FOUND, Person.class, id);
        }
    }
}
