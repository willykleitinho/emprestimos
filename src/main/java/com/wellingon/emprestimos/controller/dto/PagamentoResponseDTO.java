package com.wellingon.emprestimos.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.wellingon.emprestimos.model.enums.StatusPagamento;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagamentoResponseDTO {

  private UUID id;

  private LocalDate data;

  private BigDecimal valor;

  private String comprovante;

  private StatusPagamento status;

  private UUID idEmprestimo;
}
