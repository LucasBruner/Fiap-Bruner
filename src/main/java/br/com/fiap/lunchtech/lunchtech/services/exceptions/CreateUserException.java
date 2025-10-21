package br.com.fiap.lunchtech.lunchtech.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class CreateUserException extends DataIntegrityViolationException {
    public CreateUserException(String e) {
        super(e);
    }
}
