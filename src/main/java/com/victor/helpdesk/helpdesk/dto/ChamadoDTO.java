package com.victor.helpdesk.helpdesk.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.victor.helpdesk.helpdesk.domain.Chamado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class ChamadoDTO {

  private Integer id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataAbertura = LocalDate.now();

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataFechamento;

  @NotNull(message = "O campo prioridade é requerido ")
  private Integer prioridade;

  @NotNull(message = "O campo status é requerido ")
  private Integer status;

  @NotNull(message = "O campo titulo é requerido ")
  private String titulo;

  @NotNull(message = "O campo observacoes é requerido ")
  private String observacoes;

  @NotNull(message = "O campo tecnico é requerido ")
  private Integer tecnico;

  @NotNull(message = "O campo cliente é requerido ")
  private Integer cliente;

  private String nomeTecnico;

  private String nomeCliente;

  public ChamadoDTO(Chamado chamado) {
    this.id = chamado.getId();
    this.dataAbertura = chamado.getDataAbertura();
    this.dataFechamento = chamado.getDataFechamento();
    this.prioridade = chamado.getPrioridade().getCodigo();
    this.status = chamado.getStatus().getCodigo();
    this.titulo = chamado.getTitulo();
    this.observacoes = chamado.getObservacoes();
    this.tecnico = chamado.getTecnico().getId();
    this.cliente = chamado.getCliente().getId();
    this.nomeTecnico = chamado.getTecnico().getNome();
    this.nomeCliente = chamado.getCliente().getNome();
  }

}
