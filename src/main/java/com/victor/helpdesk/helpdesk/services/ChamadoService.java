package com.victor.helpdesk.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.helpdesk.helpdesk.domain.Chamado;
import com.victor.helpdesk.helpdesk.domain.Cliente;
import com.victor.helpdesk.helpdesk.domain.Tecnico;
import com.victor.helpdesk.helpdesk.domain.enums.Prioridade;
import com.victor.helpdesk.helpdesk.domain.enums.Status;
import com.victor.helpdesk.helpdesk.dto.ChamadoDTO;
import com.victor.helpdesk.helpdesk.repositories.ChamadoRespository;
import com.victor.helpdesk.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class ChamadoService {

  @Autowired
  private ChamadoRespository chamadoRespository;
  @Autowired
  private TecnicoService tecnicoService;
  @Autowired
  private ClienteService clienteService;

  public Chamado findById(Integer id) {

    Chamado obj = chamadoRespository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + id));
    return obj;

  }

  public List<Chamado> findAll() {

    return chamadoRespository.findAll();
  }

  public Chamado create(@Valid ChamadoDTO chamadoDto) {
    return chamadoRespository.save(newChamado(chamadoDto));
  }

  private Chamado newChamado(ChamadoDTO chamadoDTO) {

    Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());

    Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

    Chamado chamado = new Chamado();

    if (chamadoDTO.getId() != null) {
      chamado.setId(chamadoDTO.getId());

    }

    if (chamadoDTO.getStatus().equals(2)) {
      chamado.setDataFechamento(LocalDate.now());
    }

    chamado.setTecnico(tecnico);
    chamado.setCliente(cliente);
    chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
    chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
    chamado.setTitulo(chamadoDTO.getTitulo());
    chamado.setObservacoes(chamadoDTO.getObservacoes());

    return chamado;
  }

  public Chamado update(@Valid Integer id, ChamadoDTO chamadoDTO) {

    chamadoDTO.setId(id);

    Chamado oldChamado = findById(id);

    oldChamado = newChamado(chamadoDTO);

    return chamadoRespository.save(oldChamado);

  }

}
