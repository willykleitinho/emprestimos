package com.wellingon.emprestimos.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.wellingon.emprestimos.model.enums.StatusPagamento;

@Entity
@Data
@Table(name = "pagamentos")
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private LocalDate data;

  private BigDecimal valor;

  private String comprovante;

  private StatusPagamento status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_emprestimo", nullable = false)
  private Emprestimo emprestimo;
}
