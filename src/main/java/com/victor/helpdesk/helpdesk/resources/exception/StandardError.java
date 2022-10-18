package com.victor.helpdesk.helpdesk.resources.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {

  private static final long serialVersionUID = 1L;

  private String timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;

}
