package com.victor.helpdesk.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.helpdesk.helpdesk.domain.Chamado;
import com.victor.helpdesk.helpdesk.dto.ChamadoDTO;
import com.victor.helpdesk.helpdesk.services.ChamadoService;

@RestController
@RequestMapping("/chamado")
public class ChamadoResource {

  @Autowired
  private ChamadoService service;

  @GetMapping("/{id}")
  public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {

    Chamado obj = service.findById(id);

    return ResponseEntity.ok().body(new ChamadoDTO(obj));

  }

  @GetMapping()
  public ResponseEntity<List<ChamadoDTO>> findById() {

    List<Chamado> list = service.findAll();

    List<ChamadoDTO> list2 = list.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());

    return ResponseEntity.ok().body(list2);

  }

  @PostMapping
  public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO) {

    Chamado chamado = service.create(chamadoDTO);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(chamado.getId()).toUri();

    return ResponseEntity.created(uri).build();

  }

  @PutMapping("/{id}")
  public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDTO) {

    Chamado novoChamado = service.update(id, chamadoDTO);

    return ResponseEntity.ok().body(new ChamadoDTO(novoChamado));

  }

}
