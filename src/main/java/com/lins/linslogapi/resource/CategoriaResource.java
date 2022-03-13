package com.lins.linslogapi.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.linslogapi.model.Categoria;
import com.lins.linslogapi.repository.CategoriaRepository;

import lombok.AllArgsConstructor;





@RestController
@AllArgsConstructor
@RequestMapping("/categorias")
public class CategoriaResource {

	// @Autowired
	private CategoriaRepository categoriaRepository;

	@CrossOrigin(maxAge = 10, origins = {"http://localhost:8000"})
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.ok().build();
	}

	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria adicionar(@Valid @RequestBody Categoria categoria) {		
		boolean nomeEmUso = categoriaRepository.findById(categoria.getCodigo())
				.stream().anyMatch(nomeExistente -> !nomeExistente.equals(categoria));
		
		  if(nomeEmUso) { throw new RuntimeException("Já existe um categoria cadastrado com este nome");
		  }
		  return categoriaRepository.save(categoria); 
		  }
	

	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.categoriaRepository.findById(codigo).map(categoria -> ResponseEntity.ok(categoria))
				.orElse(ResponseEntity.notFound().build());
	}

}
