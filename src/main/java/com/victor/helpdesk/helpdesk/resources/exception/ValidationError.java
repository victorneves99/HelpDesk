package com.victor.helpdesk.helpdesk.resources.exception;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class ValidationError extends StandardError {

  private List<FieldMessage> errors = new ArrayList<>();

  public ValidationError(String timestamp, Integer status, String error, String message, String path) {
    super(timestamp, status, error, message, path);
  }

  public List<FieldMessage> getErrors() {
    return errors;
  }

  public void addErrors(String fieldName, String message) {
    this.errors.add(new FieldMessage(fieldName, message));
  }

}
