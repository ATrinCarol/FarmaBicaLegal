package com.generation.farmabicalegal.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmabicalegal.model.Produto;
import com.generation.farmabicalegal.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	public ProdutoRepository produtoRepository;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {

		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {

		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {

		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@GetMapping("/lab/{laboratorio}")
	public ResponseEntity<List<Produto>> getByLaboratorio(@PathVariable String laboratorio) {

		return ResponseEntity.ok(produtoRepository.findAllByLaboratorioContainingIgnoreCase(laboratorio));
	}

	@GetMapping("/nome/{nome}/quantidade/{quantidade}")
	public ResponseEntity<List<Produto>> getByNomeAndQuantidade(@PathVariable String nome,
			@PathVariable Integer quantidade) {

		return ResponseEntity.ok(produtoRepository.findByNomeAndQuantidade(nome, quantidade));

	}

	@GetMapping("/lab/{laboratorio}/preco/{preco}")
	public ResponseEntity<List<Produto>> getByLaboratorioOrPreco(@PathVariable String laboratorio,
			@PathVariable BigDecimal preco) {

		return ResponseEntity.ok(produtoRepository.findByLaboratorioOrPreco(laboratorio, preco));
	}

	@GetMapping("/preco_inicio/{inicio}/preco_fim/{fim}")
	public ResponseEntity<List<Produto>> getByBetweenPreco(@PathVariable BigDecimal inicio,
			@PathVariable BigDecimal fim) {

		return ResponseEntity.ok(produtoRepository.findByPrecoBetween(inicio, fim));
	}

	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
		return produtoRepository.findById(produto.getId())
				.map(resposta -> ResponseEntity.ok().body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {

		return produtoRepository.findById(id).map(resposta -> {
			produtoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
