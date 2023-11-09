package com.livros.BlibiotecaLivros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livros.BlibiotecaLivros.entities.Livros;
import com.livros.BlibiotecaLivros.service.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosController {

	private final LivrosService livrosservice;

	@Autowired
	public LivrosController(LivrosService livrosService) {
		this.livrosservice = livrosService;
	}

	@GetMapping("/home")
	public String paginaInicial() {
		return "index"; // Nome do seu arquivo HTML (sem a extensão)
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livros> getLivros(@PathVariable Long id) {
		Livros livros = livrosservice.getLivrosById(id);
		if (livros != null) {
			return ResponseEntity.ok(livros);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public Livros createLivros(@RequestBody Livros livros) {
		return livrosservice.saveLivros(livros);
	}

	// Utilizando o ResponseEntity e RequestEntity
	@GetMapping
	public ResponseEntity<List<Livros>> getAllLivros(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Livros> livros = livrosservice.getAllLivros();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(livros);
	}

	// update
	@PutMapping("/{id}")
	public Livros updateJogo(@PathVariable Long id, @RequestBody Livros livros) {
		return livrosservice.updateLivros(id, livros);
	}

//deletar pelo id
	@DeleteMapping("/{id}")
	public void deleteLivros(@PathVariable Long id) {
		livrosservice.deleteLivros(id);
	}
}