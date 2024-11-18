package com.enigma.ChopChop.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

    private final Validator validator;

    public void validate(Object o) {
        Set<ConstraintViolation<Object>> errors = validator.validate(o);
        if (!errors.isEmpty()) {
            throw new ConstraintViolationException(errors);
        }
    }

}