package com.livros.biblioteca.Conrollers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import com.livros.biblioteca.DTO.LivroDTO;
import com.livros.biblioteca.DTO.ResponseDto;
import com.livros.biblioteca.Models.Livros;
import com.livros.biblioteca.Repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/library/v1/books")
@RequiredArgsConstructor
public class LivroController {

    @Autowired
    public LivroRepository repositoryLivro;

    @GetMapping
    public ResponseEntity<?> livro() {
        List<Livros> livros = repositoryLivro.findAll();
        List<LivroDTO> livroDto = new ArrayList<>();

        for (Livros DtoLivro : livros) {
            LivroDTO adicionarLivros = new LivroDTO();

            adicionarLivros.setAuthor(DtoLivro.getAuthor());
            adicionarLivros.setNome(DtoLivro.getNome());
            adicionarLivros.setISBN(DtoLivro.getISBN());
            adicionarLivros.setId(DtoLivro.getId());
            adicionarLivros.setAvailable(true);

            livroDto.add(adicionarLivros);
        }
        return ResponseEntity.ok().body(livroDto);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> porID(@PathVariable Long id) {
        Livros livros = repositoryLivro.findById(id).orElse(null);

        LivroDTO livroDto = new LivroDTO();

        livroDto.setAuthor(livros.getAuthor());
        livroDto.setISBN(livros.getISBN());
        livroDto.setNome(livros.getNome());
        livroDto.setId(livros.getId());
        livroDto.setAvailable(true);

        return ResponseEntity.ok().body(livros);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<?> livro(@RequestBody @Valid LivroDTO livroDto) {

        Livros livros = new Livros();

        livros.setId(livroDto.getId());
        livros.setAuthor(livroDto.getAuthor());
        livros.setISBN(livroDto.getISBN());
        livros.setNome(livroDto.getNome());
        livros.setAvailable(true);

        repositoryLivro.save(livros);

        ResponseDto response = new ResponseDto();

        response.setAuthor(livroDto.getAuthor());
        response.setISBN(livroDto.getISBN());
        response.setNome(livroDto.getNome());
        response.setId(livroDto.getId());
        response.setAvailable(true);

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> apagarDisponibilidade(@PathVariable Long id, @Valid Livros livros) {

        LivroDTO livro = new LivroDTO();

        livro.setId(livros.getId());
        livro.setAuthor(livros.getAuthor());
        livro.setISBN(livros.getISBN());
        livro.setNome(livros.getNome());
        livro.setAvailable(false);

        repositoryLivro.deleteById(id);

        ResponseDto response = new ResponseDto();

        response.setId(livro.getId());
        response.setAuthor(livro.getAuthor());
        response.setISBN(livro.getISBN());
        response.setNome(livro.getNome());
        response.setAvailable(false);

        return ResponseEntity.ok().body(response);

        

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> atualizarLivro(@PathVariable Long id, @Valid @RequestBody LivroDTO livroDto) {

        Livros livro = new Livros();

        livro.setId(livroDto.getId());
        livro.setAuthor(livroDto.getAuthor());
        livro.setISBN(livroDto.getISBN());
        livro.setNome(livroDto.getNome());
        livro.setAvailable(true);

        repositoryLivro.save(livro);

        ResponseDto response = new ResponseDto();

        response.setId(livro.getId());
        response.setAuthor(livro.getAuthor());
        response.setISBN(livro.getISBN());
        response.setNome(livro.getNome());
        response.setAvailable(true);

        return ResponseEntity.ok().body(response);

    }

}
