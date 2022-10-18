package com.victor.helpdesk.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.victor.helpdesk.helpdesk.domain.enums.Perfil;
import com.victor.helpdesk.helpdesk.dto.TecnicoDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Tecnico extends Pessoa {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @OneToMany(mappedBy = "tecnico")
  private List<Chamado> chamados = new ArrayList<>();

  public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
    super(id, nome, cpf, email, senha);
  }

  public Tecnico() {
    super();
    addPerfil(Perfil.TECNICO);
  }

  public Tecnico(TecnicoDTO obj) {
    this.id = obj.getId();
    this.nome = obj.getNome();
    this.cpf = obj.getCpf();
    this.email = obj.getEmail();
    this.senha = obj.getSenha();
    this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    this.dataCriacao = obj.getDataCriacao();
  }

}
