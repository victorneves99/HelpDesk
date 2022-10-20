package com.victor.helpdesk.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.helpdesk.helpdesk.domain.Pessoa;
import com.victor.helpdesk.helpdesk.domain.Cliente;
import com.victor.helpdesk.helpdesk.dto.ClienteDTO;
import com.victor.helpdesk.helpdesk.repositories.PessoaRespository;
import com.victor.helpdesk.helpdesk.repositories.ClienteRespository;
import com.victor.helpdesk.helpdesk.services.exception.DataIntegrityViolationException;
import com.victor.helpdesk.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

  @Autowired
  private ClienteRespository respository;
  @Autowired
  private PessoaRespository pessoaRespository;

  public Cliente findById(Integer id) {

    Optional<Cliente> obj = respository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id:" + id));

  }

  public List<Cliente> findAll() {
    return respository.findAll();
  }

  public Cliente create(ClienteDTO objDTO) {
    objDTO.setId(null);
    validaPorCpfEEmail(objDTO);
    Cliente newOBJ = new Cliente(objDTO);
    return respository.save(newOBJ);
  }

  private void validaPorCpfEEmail(ClienteDTO objDTO) {

    Optional<Pessoa> obj = pessoaRespository.findByCpf(objDTO.getCpf());
    if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
      throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
    }

    obj = pessoaRespository.findByEmail(objDTO.getEmail());
    if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
      throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
    }

  }

  public Cliente update(Integer id, @Valid ClienteDTO objDto) {

    objDto.setId(id);
    Cliente old = findById(id);
    validaPorCpfEEmail(objDto);
    old = new Cliente(objDto);
    return respository.save(old);

  }

  public void delete(Integer id) {
    Cliente obj = findById(id);
    if (obj.getChamados().size() > 0) {
      throw new DataIntegrityViolationException("Há chamados relacionados a esse CLIENTE");

    }

    respository.deleteById(id);

  }

}
