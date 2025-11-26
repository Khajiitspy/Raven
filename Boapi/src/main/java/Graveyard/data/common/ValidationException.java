package Graveyard.data.common;

import Graveyard.data.dto.validation.FieldError;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<FieldError> errors;

    public ValidationException(List<FieldError> errors) {
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }
}
