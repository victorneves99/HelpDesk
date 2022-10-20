package com.victor.helpdesk.helpdesk.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.helpdesk.helpdesk.domain.Tecnico;
import com.victor.helpdesk.helpdesk.domain.enums.Perfil;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TecnicoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Integer id;

  @NotNull(message = "Campo nome é requerido")
  @NotBlank(message = "Campo nome não pode estar em vazio")
  protected String nome;

  @NotNull(message = "Campo CPF é requerido")
  @NotBlank(message = "Campo CPF não pode estar em vazio")
  protected String cpf;

  @NotNull(message = "Campo EMAIL é requerido")
  @NotBlank(message = "Campo EMAIL não pode estar em vazio")
  protected String email;

  @NotNull(message = "Campo SENHA é requerido")
  @NotBlank(message = "Campo SENHA não pode estar em vazio")
  protected String senha;

  protected Set<Integer> perfis = new HashSet<>();

  @JsonFormat(pattern = "dd/MM/yyyy")
  protected LocalDate dataCriacao = LocalDate.now();

  public TecnicoDTO(Tecnico obj) {
    this.id = obj.getId();
    this.nome = obj.getNome();
    this.cpf = obj.getCpf();
    this.email = obj.getEmail();
    this.senha = obj.getSenha();
    this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    this.dataCriacao = obj.getDataCriacao();
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public TecnicoDTO() {
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
