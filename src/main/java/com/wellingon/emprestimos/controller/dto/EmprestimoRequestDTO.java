package com.wellingon.emprestimos.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmprestimoRequestDTO {
  private String nome;

  private BigDecimal valor;

  private LocalDate data;

  private Double taxaDeJuros;

}
