package com.lins.linslogapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.lins.linslogapi.model.Pessoa;
import com.lins.linslogapi.repository.PessoaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PessoaService {
	
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualiza(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = this.pessoaRepository.findById(codigo)
			      .orElseThrow(() -> new EmptyResultDataAccessException(1));

			  BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");	
		return pessoaRepository.save(pessoaSalva);	
		
	}
	
	public Optional<Pessoa> atualizarPropriedadeAtivo(Long codigo, @PathVariable Boolean ativo) {
		   buscarPessoaPeloCodigo(codigo);
		   Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		   pessoaSalva.get().setAtivo(ativo);
		   return pessoaSalva;		
	}
	
	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		 return this.pessoaRepository.findById(codigo)
		.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	

}
