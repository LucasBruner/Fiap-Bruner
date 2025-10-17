package br.com.fiap.lunchtech.lunchtech.services.exceptions;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String e) {
        super(e);
    }
}
