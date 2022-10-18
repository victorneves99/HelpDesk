package com.victor.helpdesk.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.helpdesk.helpdesk.domain.Tecnico;
import com.victor.helpdesk.helpdesk.repositories.TecnicoRespository;
import com.victor.helpdesk.helpdesk.services.TecnicoService;

@RestController
@RequestMapping("/tecnico")
public class TecnicoResource {

  @Autowired
  private TecnicoService service;

  @GetMapping("/{id}")
  public ResponseEntity<Tecnico> findById(@PathVariable Integer id) {

    Tecnico obj = service.findById(id);

    return ResponseEntity.status(HttpStatus.OK).body(obj);

  }

}
