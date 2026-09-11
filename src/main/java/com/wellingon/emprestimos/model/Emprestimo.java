package com.wellingon.emprestimos.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "emprestimos")
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String nome;

  private BigDecimal valor;

  private LocalDate data;

  private Double taxaDeJuros;

}
