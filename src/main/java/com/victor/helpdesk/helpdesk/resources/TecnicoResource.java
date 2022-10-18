package com.victor.helpdesk.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.helpdesk.helpdesk.domain.Tecnico;
import com.victor.helpdesk.helpdesk.dto.TecnicoDTO;
import com.victor.helpdesk.helpdesk.repositories.TecnicoRespository;
import com.victor.helpdesk.helpdesk.services.TecnicoService;

@RestController
@RequestMapping("/tecnico")
public class TecnicoResource {

  @Autowired
  private TecnicoService service;

  @GetMapping("/{id}")
  public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {

    Tecnico obj = service.findById(id);

    return ResponseEntity.status(HttpStatus.OK).body(new TecnicoDTO(obj));

  }

  @GetMapping
  public ResponseEntity<List<TecnicoDTO>> findAll() {

    List<Tecnico> list = service.findAll();
    List<TecnicoDTO> listDTO = list.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);

  }

}
