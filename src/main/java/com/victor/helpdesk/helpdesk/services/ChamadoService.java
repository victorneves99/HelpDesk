package com.victor.helpdesk.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.helpdesk.helpdesk.domain.Chamado;
import com.victor.helpdesk.helpdesk.repositories.ChamadoRespository;
import com.victor.helpdesk.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class ChamadoService {

  @Autowired
  private ChamadoRespository chamadoRespository;

  public Chamado findById(Integer id) {

    Chamado obj = chamadoRespository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + id));
    return obj;

  }

  public List<Chamado> findAll() {

    return chamadoRespository.findAll();
  }

}
