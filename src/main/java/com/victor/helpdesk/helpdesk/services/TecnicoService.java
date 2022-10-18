package com.victor.helpdesk.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.helpdesk.helpdesk.domain.Tecnico;
import com.victor.helpdesk.helpdesk.repositories.TecnicoRespository;
import com.victor.helpdesk.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

  @Autowired
  private TecnicoRespository respository;

  public Tecnico findById(Integer id) {

    Optional<Tecnico> obj = respository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id:" + id));

  }

}
