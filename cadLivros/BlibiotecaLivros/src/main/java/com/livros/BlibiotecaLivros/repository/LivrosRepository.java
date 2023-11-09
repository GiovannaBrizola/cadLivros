package com.livros.BlibiotecaLivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livros.BlibiotecaLivros.entities.Livros;


public interface LivrosRepository extends JpaRepository<Livros, Long> {

}
