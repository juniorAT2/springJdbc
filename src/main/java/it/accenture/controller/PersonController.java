package it.accenture.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.accenture.dto.PersonDto;
import it.accenture.exception.EntityNotFoundException;
import it.accenture.mapstruct.PersonMapper;
import it.accenture.model.Person;
import it.accenture.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;
@CrossOrigin
@Controller
@RequestMapping(value="/person")
public class PersonController {
    private PersonService ps;

    @Autowired
    public PersonController(PersonService ps) {
        this.ps = ps;
    }

    @ApiOperation(value = "Get method", notes = "Returns all storage person on Database ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Database is empty!")
    })
    @GetMapping(value="/")
    public ResponseEntity<?> getAll () {
        var person = ps.findAll();
        var dtos = StreamSupport.stream(person.spliterator(), false)
                .map(PersonMapper.INSTANCE::toPersonDTO).toList();
        return ResponseEntity.ok(dtos);
    }
    @PostMapping("/")
    @ApiOperation(value = "Post method ", notes = "Save item on Database ")
    @ApiParam(required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added"),
            @ApiResponse(code = 404, message = "Something went wrong, nothing added please try again!")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PersonDto personDto) {
        Person person = PersonMapper.INSTANCE.fromPersonDto(personDto);
        try {
            ps.saveOrUpdate(person);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @ApiOperation(value = "Put method", notes = "Update storage person on Database ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Something went wrong, nothing updated please try again!")
    })
    @PutMapping(value="/{id}")
    public ResponseEntity<?> update(@RequestBody PersonDto personDto, @PathVariable Integer id) {
        Person p = PersonMapper.INSTANCE.fromPersonDto(personDto);
        try {
            Person pSaved = ps.saveOrUpdate(p);
            PersonDto pDto = PersonMapper.INSTANCE.toPersonDTO(p);
            return ResponseEntity.ok(pDto);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @ApiOperation(value = "Delete method", notes = "Delete storage person on Database matched by id!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Something went wrong, nothing deleted please try again!")
    })
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            ps.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

