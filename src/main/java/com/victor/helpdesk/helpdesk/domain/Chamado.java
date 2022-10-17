package com.victor.helpdesk.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.helpdesk.helpdesk.domain.enums.Prioridade;
import com.victor.helpdesk.helpdesk.domain.enums.Status;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
@Setter
@Entity
public class Chamado implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataAbertura = LocalDate.now();

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataFechamento;

  private Prioridade prioridade;
  private Status status;
  private String titulo;
  private String observacoes;

  @ManyToOne
  @JoinColumn(name = "tecnico_id")
  private Tecnico tecnico;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  public Chamado() {
    super();
  }

  public Chamado(Integer id, Prioridade prioridade, Status status,
      String titulo, String observacoes, Tecnico tecnico, Cliente cliente) {
    super();
    this.id = id;
    this.prioridade = prioridade;
    this.status = status;
    this.titulo = titulo;
    this.observacoes = observacoes;
    this.tecnico = tecnico;
    this.cliente = cliente;
  }

}
