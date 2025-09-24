package br.com.fiap.locatech.locatech.controllers.handlers;

import br.com.fiap.locatech.locatech.dtos.MethodArgumentDTO;
import br.com.fiap.locatech.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MethodArgumentDTO> handlerResourceNotFoundException(MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new MethodArgumentDTO(errors, status.value()));
    }
}
