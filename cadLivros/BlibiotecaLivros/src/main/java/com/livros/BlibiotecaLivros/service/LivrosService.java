package com.livros.BlibiotecaLivros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livros.BlibiotecaLivros.entities.Livros;
import com.livros.BlibiotecaLivros.repository.LivrosRepository;

@Service
public class LivrosService {
	private final LivrosRepository livrosRepository;

	// construtor que recebe a dependencia
	@Autowired
	public LivrosService(LivrosRepository livrosRepository) {
		this.livrosRepository = livrosRepository;
	}

	// preparando as buscas pelo id
	public Livros getLivrosById(Long id) {
		return livrosRepository.findById(id).orElse(null);
	}

	// preparando a busca geral
	public List<Livros> getAllLivros() {
		return livrosRepository.findAll();
	}

	// salvando
	public Livros saveLivros(Livros livros) {
		return livrosRepository.save(livros);
	}

	// excluir
	public void deleteLivros(Long id) {
		livrosRepository.deleteById(id);
	}

	// fazendo o update do jogo com o optional
	public Livros updateLivros(Long id, Livros novoLivros) {
		Optional<Livros> livrosOptional = livrosRepository.findById(id);
		if (livrosOptional.isPresent()) {
			Livros livrosExistente = livrosOptional.get();
			livrosExistente.setIsbn(novoLivros.getIsbn());
			livrosExistente.setDescricao(novoLivros.getDescricao());
			return livrosRepository.save(livrosExistente);
		} else {
			return null;
		}

	}
}
