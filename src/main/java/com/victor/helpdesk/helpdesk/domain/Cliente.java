package com.victor.helpdesk.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cliente extends Pessoa {

  private List<Chamado> chamados = new ArrayList<>();

  public Cliente(Integer id, String nome, String cpf, String email, String senha) {
    super(id, nome, cpf, email, senha);
  }

  public Cliente() {
    super();
  }

}
