package com.wellingon.emprestimos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellingon.emprestimos.model.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID> {

}
