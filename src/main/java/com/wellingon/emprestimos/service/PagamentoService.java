package com.wellingon.emprestimos.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellingon.emprestimos.controller.dto.PagamentoRequestDTO;
import com.wellingon.emprestimos.controller.dto.PagamentoResponseDTO;
import com.wellingon.emprestimos.model.Emprestimo;
import com.wellingon.emprestimos.model.Pagamento;
import com.wellingon.emprestimos.repository.EmprestimoRepository;
import com.wellingon.emprestimos.repository.PagamentoRepository;

@Service
public class PagamentoService {

  @Autowired
  PagamentoRepository pagamentoRepository;

  @Autowired
  EmprestimoRepository emprestimoRepository;

  @Autowired
  ModelMapper mapper;

  public PagamentoResponseDTO criarPagamento(PagamentoRequestDTO requestDTO) {

    Emprestimo emprestimo = emprestimoRepository.findById(requestDTO.getIdEmprestimo()).orElse(null);

    if (emprestimo == null)
      return null;

    Pagamento pagamento = mapper.map(requestDTO, Pagamento.class);

    pagamento.setEmprestimo(emprestimo);

    pagamento = pagamentoRepository.save(pagamento);

    return mapper.map(pagamento, PagamentoResponseDTO.class);
  }

  public List<PagamentoResponseDTO> listarPagamentos() {
    return pagamentoRepository.findAll().stream().map(pagamento -> mapper.map(pagamento, PagamentoResponseDTO.class))
        .toList();
  }
}
