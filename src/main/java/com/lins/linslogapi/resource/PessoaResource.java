package com.lins.linslogapi.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.linslogapi.model.Pessoa;
import com.lins.linslogapi.repository.PessoaRepository;
import com.lins.linslogapi.service.PessoaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/pessoas")
public class PessoaResource {
	
	private PessoaRepository pessoaRepository;
	
	private PessoaService pessoaService;
	

	@GetMapping
	public List<Pessoa> listar(){
		return pessoaRepository.findAll();
		
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa){
		Pessoa pessoSalva = pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(pessoSalva);
		
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
	this.pessoaRepository.deleteById(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPorCodigo(@PathVariable Long codigo){
		 Pessoa pessoaSalva = pessoaRepository.getById(codigo);
		 return pessoaSalva !=null ? ResponseEntity.ok(pessoaSalva):ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	public Pessoa atualizar(@PathVariable Long codigo, @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualiza(codigo, pessoa);
		
		  return this.pessoaRepository.save(pessoaSalva);
		}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}
	
	
	

}
