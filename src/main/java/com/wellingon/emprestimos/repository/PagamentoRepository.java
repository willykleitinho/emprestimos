package com.wellingon.emprestimos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellingon.emprestimos.model.Emprestimo;
import com.wellingon.emprestimos.model.Pagamento;
import com.wellingon.emprestimos.model.enums.StatusPagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
  public List<Pagamento> findAllByStatusAndEmprestimoOrderByDataAsc(StatusPagamento status, Emprestimo emprestimo);

}
