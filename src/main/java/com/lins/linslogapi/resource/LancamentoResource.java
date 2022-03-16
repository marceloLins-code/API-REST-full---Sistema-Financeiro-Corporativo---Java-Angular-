package com.lins.linslogapi.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.linslogapi.exceptionHandler.linslogExceptionHandler.Erro;
import com.lins.linslogapi.model.Lancamento;
import com.lins.linslogapi.repository.LancamentoRepository;
import com.lins.linslogapi.repository.filter.LancamentoFilter;
import com.lins.linslogapi.repository.projection.ResumoLancamento;
import com.lins.linslogapi.service.LancamentoService;
import com.lins.linslogapi.service.exception.PessoaInexistenteOuInativaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {


	private LancamentoRepository lancamentoRepository;

	private MessageSource messageSource;

	private LancamentoService lancamentoService;

	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {

		return lancamentoRepository.filtrar(lancamentoFilter, pageable);

	}

	/*
	 * @GetMapping public ResponseEntity<?> listar() { List<Lancamento> categorias =
	 * lancamentoRepository.findAll(); return !categorias.isEmpty() ?
	 * ResponseEntity.ok(categorias) : ResponseEntity.ok().build(); }
	 */

	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
		return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
	}
	

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.resumir(lancamentoFilter, pageable);
	}

	@PostMapping
	public ResponseEntity<Lancamento> adicionar(@Valid @RequestBody Lancamento lancamento) {
		Lancamento novoLancamento = lancamentoService.criar(lancamento);
		lancamentoRepository.save(novoLancamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoLancamento);
	}

	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlerPessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("Pessoa.inexistente-ou-inativa", null,
				LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);

	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long codigo) {
		if (!lancamentoRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}		
		lancamentoService.excluir(codigo);		
		return ResponseEntity.noContent().build();
	}

}
