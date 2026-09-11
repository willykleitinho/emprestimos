package com.wellingon.emprestimos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellingon.emprestimos.controller.dto.PagamentoRequestDTO;
import com.wellingon.emprestimos.controller.dto.PagamentoResponseDTO;
import com.wellingon.emprestimos.service.PagamentoService;

// @RestController
// @RequestMapping("/pagamentos")
public class PagamentoController {

  @Autowired
  private PagamentoService pagamentoService;

  @PostMapping
  public PagamentoResponseDTO criarPagamento(PagamentoRequestDTO requestDTO) {
    return pagamentoService.criarPagamento(requestDTO);
  }

  @GetMapping
  public List<PagamentoResponseDTO> listarPagamentos() {
    return pagamentoService.listarPagamentos();
  }
}
