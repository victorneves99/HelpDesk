package com.victor.helpdesk.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.helpdesk.helpdesk.domain.Pessoa;
import com.victor.helpdesk.helpdesk.domain.Tecnico;

public interface TecnicoRespository extends JpaRepository<Tecnico, Integer> {

}
