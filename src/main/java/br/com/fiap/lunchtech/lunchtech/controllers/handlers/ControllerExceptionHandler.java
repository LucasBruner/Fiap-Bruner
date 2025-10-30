package br.com.fiap.lunchtech.lunchtech.controllers.handlers;

import br.com.fiap.lunchtech.lunchtech.services.exceptions.CreateUserException;
import br.com.fiap.lunchtech.lunchtech.services.exceptions.InvalidLoginException;
import br.com.fiap.lunchtech.lunchtech.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handlerResourceNotFoundException(ResourceNotFoundException e) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Erro ao salvar usuário!");
        problem.setDetail(e.getMessage());
        problem.setType(URI.create("http://localhost:8080/v1/usuarios"));
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handlerResourceNotFoundException(MethodArgumentNotValidException e) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Erro ao salvar usuário!");
        problem.setDetail(e.getTitleMessageCode());
        problem.setType(URI.create("http://localhost:8080/v1/usuarios"));

        List<String> errors = new ArrayList<String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        problem.setProperty("errors", errors);

        return problem;
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ProblemDetail handlerInvalidLoginException(InvalidLoginException e) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problem.setTitle("Erro ao realizar login!");
        problem.setDetail(e.getMessage());
        problem.setType(URI.create("http://localhost:8080/v1/login"));
        return problem;
    }

    @ExceptionHandler(CreateUserException.class)
    public ProblemDetail handlerCreateUserException(CreateUserException e) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.METHOD_NOT_ALLOWED);
        problem.setTitle("Erro ao salvar usuário!");
        problem.setDetail(e.getMessage());
        problem.setType(URI.create("http://localhost:8080/v1/usuarios"));
        return problem;
    }
}
