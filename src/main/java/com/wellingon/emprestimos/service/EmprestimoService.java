package com.wellingon.emprestimos.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellingon.emprestimos.controller.dto.EmprestimoRequestDTO;
import com.wellingon.emprestimos.controller.dto.EmprestimoResponseDTO;
import com.wellingon.emprestimos.model.Emprestimo;
import com.wellingon.emprestimos.model.Pagamento;
import com.wellingon.emprestimos.model.enums.StatusPagamento;
import com.wellingon.emprestimos.repository.EmprestimoRepository;
import com.wellingon.emprestimos.repository.PagamentoRepository;

@Service
public class EmprestimoService {

  @Autowired
  private EmprestimoRepository repository;

  @Autowired
  private PagamentoRepository pagamentoRepository;

  @Autowired
  private ModelMapper mapper;

  public List<EmprestimoResponseDTO> listarEmprestimos() {
    return repository.findAll().stream().map(emprestimo -> {

      EmprestimoResponseDTO responseDTO = mapper.map(emprestimo, EmprestimoResponseDTO.class);
      List<Pagamento> pagamentos = pagamentoRepository
          .findAllByStatusAndEmprestimoOrderByDataAsc(StatusPagamento.APROVADO, emprestimo);

      // claudinho deepseek v4 pro que fez
      // --- on-the-fly simple interest calculation ---
      BigDecimal principal = emprestimo.getValor();
      BigDecimal jurosTotal = BigDecimal.ZERO;
      LocalDate currentDate = emprestimo.getData();
      LocalDate hoje = LocalDate.now();
      BigDecimal taxaDiaria = BigDecimal.valueOf(emprestimo.getTaxaDeJuros() / 100.0)
          .divide(BigDecimal.valueOf(30), 10, RoundingMode.HALF_EVEN);

      for (Pagamento p : pagamentos) {
        long days = ChronoUnit.DAYS.between(currentDate, p.getData());

        if (days > 0) {
          BigDecimal juros = principal
              .multiply(taxaDiaria)
              .multiply(BigDecimal.valueOf(days));
          jurosTotal = jurosTotal.add(juros);
        }

        principal = principal.subtract(p.getValor());
        currentDate = p.getData();
      }

      long days = ChronoUnit.DAYS.between(currentDate, hoje);
      if (days > 0) {
        BigDecimal juros = principal
            .multiply(taxaDiaria)
            .multiply(BigDecimal.valueOf(days));
        jurosTotal = jurosTotal.add(juros);
      }

      responseDTO.setJurosAcumulados(jurosTotal.setScale(2, RoundingMode.HALF_EVEN));
      responseDTO.setValorTotalDevido(principal.add(jurosTotal).setScale(2, RoundingMode.HALF_EVEN));
      // --- end calculation ---

      return responseDTO;
    }).toList();
  }

  public EmprestimoResponseDTO criarEmprestimo(EmprestimoRequestDTO requestDTO) {

    Emprestimo newEmprestimo = mapper.map(requestDTO, Emprestimo.class);

    newEmprestimo = repository.save(newEmprestimo);

    EmprestimoResponseDTO response = mapper.map(newEmprestimo, EmprestimoResponseDTO.class);

    return response;
  }

}
