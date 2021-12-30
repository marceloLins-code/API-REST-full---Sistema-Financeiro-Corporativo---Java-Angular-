package com.lins.linslogapi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lins.linslogapi.model.Lancamento;
import com.lins.linslogapi.model.Pessoa;
import com.lins.linslogapi.repository.LancamentoRepository;
import com.lins.linslogapi.repository.PessoaRepository;
import com.lins.linslogapi.service.exception.PessoaInexistenteOuInativaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LancamentoService {
	
	private LancamentoRepository lancamentoRepository;
	
	private PessoaRepository pessoaRepository;
	
	public Lancamento criar(Lancamento lancamento) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if (pessoaSalva == null || !pessoaSalva.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
			
		}	
		return lancamentoRepository.save(lancamento);
		
	}

	public void excluir(Long codigo) {
		lancamentoRepository.deleteById(codigo);		
	}
	
	
	

}
