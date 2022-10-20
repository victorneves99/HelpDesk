package com.victor.helpdesk.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.helpdesk.helpdesk.domain.Tecnico;
import com.victor.helpdesk.helpdesk.dto.TecnicoDTO;
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

  @PostMapping
  public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {

    Tecnico newOBJ = service.create(objDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOBJ.getId()).toUri();
    return ResponseEntity.created(uri).build();

  }

  @PutMapping("/{id}")
  public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDto) {

    Tecnico obj = service.update(id, objDto);
    return ResponseEntity.ok().body(new TecnicoDTO(obj));

  }

}
