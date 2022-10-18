package com.victor.helpdesk.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.helpdesk.helpdesk.domain.Pessoa;

public interface PessoaRespository extends JpaRepository<Pessoa, Integer> {

}
