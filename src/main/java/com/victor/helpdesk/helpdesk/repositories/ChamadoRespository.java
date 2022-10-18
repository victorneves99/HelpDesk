package com.victor.helpdesk.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.helpdesk.helpdesk.domain.Chamado;

public interface ChamadoRespository extends JpaRepository<Chamado, Integer> {

}
