package it.accenture.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message, Class<?> entity, Integer id) {
        super(String.format(message, entity.getSimpleName(), id));
    }

}
