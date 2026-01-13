package com.shpyakin.javassau.controller;

import com.shpyakin.javassau.model.AuthorDTO;
import com.shpyakin.javassau.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getById(@PathVariable("id") Long id) {
        AuthorDTO author = authorService.getById(id);
        return author != null
            ? ResponseEntity.ok(author)
             : ResponseEntity.notFound().build();
    }

    @PostMapping
    public AuthorDTO create(@RequestBody AuthorDTO authorDTO) {
        return authorService.create(authorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable("id") Long id, @RequestBody AuthorDTO authorDTO) {
        AuthorDTO updatedAuthor = authorService.update(id, authorDTO);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}