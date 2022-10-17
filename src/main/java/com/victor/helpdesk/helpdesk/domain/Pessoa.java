package com.victor.helpdesk.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.helpdesk.helpdesk.domain.enums.Perfil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@Entity
public abstract class Pessoa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;
  protected String nome;

  @Column(unique = true)
  protected String cpf;

  @Column(unique = true)
  protected String email;
  protected String senha;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "PERFIS")
  protected Set<Integer> perfis = new HashSet<>();

  @JsonFormat(pattern = "dd/MM/yyyy")
  protected LocalDate dataCriacao = LocalDate.now();

  public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.senha = senha;
    addPerfil(Perfil.CLIENTE);
  }

  public Pessoa() {
    super();
    addPerfil(Perfil.CLIENTE);
  }

  public Set<Perfil> getPerfis() {
    return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
  }

  public void addPerfil(Perfil perfil) {
    this.perfis.add(perfil.getCodigo());
  }

}
