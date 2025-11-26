package Graveyard.interfaces;

import Graveyard.data.dto.validation.FieldError;

import java.util.List;

public interface Validator<T> {
    List<FieldError> validate(T object);
}
