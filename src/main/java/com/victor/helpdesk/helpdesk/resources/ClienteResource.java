package com.victor.helpdesk.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.helpdesk.helpdesk.domain.Cliente;
import com.victor.helpdesk.helpdesk.dto.ClienteDTO;
import com.victor.helpdesk.helpdesk.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

  @Autowired
  private ClienteService service;

  @GetMapping("/{id}")
  public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {

    Cliente obj = service.findById(id);

    return ResponseEntity.status(HttpStatus.OK).body(new ClienteDTO(obj));

  }

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> findAll() {

    List<Cliente> list = service.findAll();
    List<ClienteDTO> listDTO = list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);

  }

  @PostMapping
  public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO) {

    Cliente newOBJ = service.create(objDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOBJ.getId()).toUri();
    return ResponseEntity.created(uri).build();

  }

  @PutMapping("/{id}")
  public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDto) {

    Cliente obj = service.update(id, objDto);
    return ResponseEntity.ok().body(new ClienteDTO(obj));

  }

  @DeleteMapping("{id}")
  public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {

    service.delete(id);
    return ResponseEntity.noContent().build();

  }

}
