package com.victor.helpdesk.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.helpdesk.helpdesk.domain.Chamado;
import com.victor.helpdesk.helpdesk.domain.Cliente;
import com.victor.helpdesk.helpdesk.domain.Tecnico;
import com.victor.helpdesk.helpdesk.domain.enums.Perfil;
import com.victor.helpdesk.helpdesk.domain.enums.Prioridade;
import com.victor.helpdesk.helpdesk.domain.enums.Status;
import com.victor.helpdesk.helpdesk.repositories.ChamadoRespository;
import com.victor.helpdesk.helpdesk.repositories.ClienteRespository;
import com.victor.helpdesk.helpdesk.repositories.TecnicoRespository;

@Service
public class DBService {

  @Autowired
  private TecnicoRespository tecnicoRespository;
  @Autowired
  private ClienteRespository clienteRespository;
  @Autowired
  private ChamadoRespository chamadoRespository;

  public void instanciaDb() {

    Tecnico tec1 = new Tecnico(null, "Victor Neves", "272.609.670-05", "victor@email.com", "123");
    tec1.addPerfil(Perfil.ADMIN);

    Cliente cli1 = new Cliente(null, "teste", "790.555.800-23", "teste@email.com", "123");

    Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "primeiro chamado", tec1, cli1);

    tecnicoRespository.saveAll(Arrays.asList(tec1));
    clienteRespository.saveAll(Arrays.asList(cli1));
    chamadoRespository.saveAll(Arrays.asList(c1));

  }

}