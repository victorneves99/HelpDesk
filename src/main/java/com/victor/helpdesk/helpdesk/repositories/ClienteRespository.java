package com.victor.helpdesk.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.helpdesk.helpdesk.domain.Cliente;

public interface ClienteRespository extends JpaRepository<Cliente, Integer> {

}
