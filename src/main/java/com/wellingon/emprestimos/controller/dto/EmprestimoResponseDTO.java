package com.wellingon.emprestimos.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmprestimoResponseDTO {

  private UUID id;

  private String nome;

  private BigDecimal valor;

  private LocalDate data;

  private Double taxaDeJuros;

  private BigDecimal valorTotalDevido;

  private BigDecimal jurosAcumulados;

}
