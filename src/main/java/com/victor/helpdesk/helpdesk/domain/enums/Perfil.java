package com.victor.helpdesk.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Perfil {

  ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");

  private Integer codigo;
  private String descricao;

  public static Perfil toEnum(Integer cod) {

    if (cod == null) {
      return null;
    }

    for (Perfil x : Perfil.values()) {
      if (cod.equals(x.getCodigo())) {
        return x;
      }

    }

    throw new IllegalArgumentException("Perfil Invalidao");

  }

}
