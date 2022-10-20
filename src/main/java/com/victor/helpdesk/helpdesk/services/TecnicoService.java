package com.victor.helpdesk.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.helpdesk.helpdesk.domain.Pessoa;
import com.victor.helpdesk.helpdesk.domain.Tecnico;
import com.victor.helpdesk.helpdesk.dto.TecnicoDTO;
import com.victor.helpdesk.helpdesk.repositories.PessoaRespository;
import com.victor.helpdesk.helpdesk.repositories.TecnicoRespository;
import com.victor.helpdesk.helpdesk.services.exception.DataIntegrityViolationException;
import com.victor.helpdesk.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

  @Autowired
  private TecnicoRespository respository;
  @Autowired
  private PessoaRespository pessoaRespository;

  public Tecnico findById(Integer id) {

    Optional<Tecnico> obj = respository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id:" + id));

  }

  public List<Tecnico> findAll() {
    return respository.findAll();
  }

  public Tecnico create(TecnicoDTO objDTO) {
    objDTO.setId(null);
    validaPorCpfEEmail(objDTO);
    Tecnico newOBJ = new Tecnico(objDTO);
    return respository.save(newOBJ);
  }

  private void validaPorCpfEEmail(TecnicoDTO objDTO) {

    Optional<Pessoa> obj = pessoaRespository.findByCpf(objDTO.getCpf());
    if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
      throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
    }

    obj = pessoaRespository.findByEmail(objDTO.getEmail());
    if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
      throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
    }

  }

  public Tecnico update(Integer id, @Valid TecnicoDTO objDto) {

    objDto.setId(id);
    Tecnico old = findById(id);
    validaPorCpfEEmail(objDto);
    old = new Tecnico(objDto);
    return respository.save(old);

  }

}
