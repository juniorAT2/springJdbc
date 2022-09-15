package it.accenture.controller;

import it.accenture.model.Person;
import it.accenture.model.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@CrossOrigin
@RequestMapping(value="/person")
public class PersonController {
    private PersonService ps;

    @Autowired
    public PersonController(PersonService ps) {
        this.ps = ps;
    }

    @GetMapping(value="/")
    public List<Person> getAll () {
        return ps.findAll();
    }
}
