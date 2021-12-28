package com.livros.biblioteca.Repositories;

import com.livros.biblioteca.Models.Livros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livros, Long>{

    
}
