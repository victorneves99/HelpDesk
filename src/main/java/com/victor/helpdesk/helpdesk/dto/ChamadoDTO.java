package com.victor.helpdesk.helpdesk.dto;

import java.time.LocalDate;

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

  private Integer prioridade;

  private Integer status;

  private String titulo;

  private String observacoes;

  private Integer tecnico;

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
