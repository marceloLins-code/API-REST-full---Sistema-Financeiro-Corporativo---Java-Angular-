package com.lins.linslogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lins.linslogapi.model.Lancamento;
import com.lins.linslogapi.repository.lancamento.LancamentoRepositoryQuery;



@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{
	
	

}
