package com.victor.helpdesk.helpdesk.resources.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.victor.helpdesk.helpdesk.services.exception.DataIntegrityViolationException;
import com.victor.helpdesk.helpdesk.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {

    StandardError error = new StandardError(LocalDateTime.now().toString(), HttpStatus.NOT_FOUND.value(),
        "Object not found", ex.getMessage(), request.getRequestURI());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
      HttpServletRequest request) {

    StandardError error = new StandardError(LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST.value(),
        "Violação de dados.", ex.getMessage(), request.getRequestURI());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> validationErros(MethodArgumentNotValidException ex,
      HttpServletRequest request) {

    ValidationError errors = new ValidationError(LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST.value(),
        "Erro na validação dos campos", "Erro", request.getRequestURI());

    for (FieldError x : ex.getBindingResult().getFieldErrors()) {
      errors.addErrors(x.getField(), x.getDefaultMessage());
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

}
