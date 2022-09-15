package it.accenture.controller;

import it.accenture.dto.CityDto;
import it.accenture.exception.EntityNotFoundException;
import it.accenture.mapstruct.CityMapper;
import it.accenture.model.City;
import it.accenture.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@CrossOrigin
@Controller
@RequestMapping(value="/city")
public class CityController {

    private CityService cs;

    @Autowired
    public CityController(CityService cs) {
        this.cs = cs;
    }

    @GetMapping(value="/")
    public ResponseEntity<?> getAll() {
        var city = cs.findAll();
        var dtos = StreamSupport.stream(city.spliterator(),false)
                                            .map(CityMapper.INSTANCE::toCityDTO).toList();
        return ResponseEntity.ok(dtos);
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CityDto cityDto) {
        City city = CityMapper.INSTANCE.fromCityDTO(cityDto);
        try {
            cs.saveOrUpdate(city);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody CityDto cityDto, @PathVariable Integer id) {
        City city = CityMapper.INSTANCE.fromCityDTO(cityDto);
        try {
            City cSaved = cs.saveOrUpdate(city);
            CityDto cDto = CityMapper.INSTANCE.toCityDTO(city);
            return ResponseEntity.ok(cDto);

        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            cs.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
