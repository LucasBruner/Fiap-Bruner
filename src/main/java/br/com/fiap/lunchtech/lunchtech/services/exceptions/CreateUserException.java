package br.com.fiap.lunchtech.lunchtech.services.exceptions;

public class CreateUserException extends RuntimeException {
    public CreateUserException(String e) {
        super(e);
    }
}
