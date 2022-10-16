package com.victor.helpdesk.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Status {

  ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

  private Integer codigo;
  private String descricao;

  public static Status toEnum(Integer cod) {

    if (cod == null) {
      return null;
    }

    for (Status x : Status.values()) {
      if (cod.equals(x.getCodigo())) {
        return x;
      }

    }

    throw new IllegalArgumentException("Status Invalidao");

  }

}
