package com.wellingon.emprestimos.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wellingon.emprestimos.controller.dto.EmprestimoResponseDTO;
import com.wellingon.emprestimos.service.EmprestimoService;

// implementado pelo claudinho deepseek v4 pro
@Controller
@RequestMapping("/")
public class UIController {

  @Autowired
  private EmprestimoService emprestimoService;

  private static final NumberFormat BRL = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

  @GetMapping("/")
  public String index(Model model) {

    List<EmprestimoResponseDTO> emprestimos = emprestimoService.listarEmprestimos();

    BigDecimal totalEmprestado = BigDecimal.ZERO;
    BigDecimal totalJuros = BigDecimal.ZERO;
    BigDecimal totalDevido = BigDecimal.ZERO;

    for (EmprestimoResponseDTO e : emprestimos) {
      totalEmprestado = totalEmprestado.add(e.getValor());
      totalJuros = totalJuros.add(e.getJurosAcumulados());
      totalDevido = totalDevido.add(e.getValorTotalDevido());
    }

    model.addAttribute("emprestimos", emprestimos);
    model.addAttribute("quantidade", emprestimos.size());
    model.addAttribute("totalEmprestado", BRL.format(totalEmprestado));
    model.addAttribute("totalJuros", BRL.format(totalJuros));
    model.addAttribute("totalDevido", BRL.format(totalDevido));

    return "index";
  }
}
