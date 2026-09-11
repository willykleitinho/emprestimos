package com.wellingon.emprestimos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellingon.emprestimos.controller.dto.EmprestimoRequestDTO;
import com.wellingon.emprestimos.controller.dto.EmprestimoResponseDTO;
import com.wellingon.emprestimos.service.EmprestimoService;

@RestController
@RequestMapping("emprestimos")
public class EmprestimoController {

  @Autowired
  private EmprestimoService emprestimoService;

  @GetMapping
  public List<EmprestimoResponseDTO> listarEmprestimos() {
    return emprestimoService.listarEmprestimos();
  }

  @PostMapping
  public EmprestimoResponseDTO criarEmprestimo(@RequestBody EmprestimoRequestDTO requestDTO) {
    return emprestimoService.criarEmprestimo(requestDTO);
  }

}
