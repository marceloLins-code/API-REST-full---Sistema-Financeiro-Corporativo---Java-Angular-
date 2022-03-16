package com.lins.linslogapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lins.linslogapi.model.Lancamento;
import com.lins.linslogapi.repository.filter.LancamentoFilter;
import com.lins.linslogapi.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
	

}
