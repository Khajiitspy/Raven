package Graveyard.interfaces;

import Graveyard.data.dto.validation.FieldError;

@FunctionalInterface
public interface ValidationRule<T> {
    FieldError validate(T object);
}
