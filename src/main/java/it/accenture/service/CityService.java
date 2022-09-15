package it.accenture.service;

import it.accenture.exception.EntityNotFoundException;
import it.accenture.model.City;
import it.accenture.repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    public static final String ERROR_NOT_FOUND = "L'entità %s con id %d non esiste";
    private CityRepo cr;

    @Autowired
    public CityService(CityRepo cr) {
        this.cr = cr;
    }

    public Iterable<City> findAll() {
        return cr.findAll();
    }
    public City saveOrUpdate(City c) throws EntityNotFoundException {
        if (c.getId() != null && !c.getId().equals(0) && !cr.existsById(c.getId())) {
            throw new EntityNotFoundException(ERROR_NOT_FOUND, City.class, c.getId());
        }
        return cr.save(c);
    }

    public void deleteById(Integer id) throws EntityNotFoundException {
        if(cr.existsById(id)) {
            cr.deleteById(id);
        } else {
            throw new EntityNotFoundException(ERROR_NOT_FOUND, City.class, id);
        }
    }
}
