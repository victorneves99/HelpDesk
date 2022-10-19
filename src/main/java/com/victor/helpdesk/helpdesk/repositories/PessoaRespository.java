package com.victor.helpdesk.helpdesk.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.helpdesk.helpdesk.domain.Pessoa;

public interface PessoaRespository extends JpaRepository<Pessoa, Integer> {

  Optional<Pessoa> findByCpf(String cpf);

  Optional<Pessoa> findByEmail(String email);
}
