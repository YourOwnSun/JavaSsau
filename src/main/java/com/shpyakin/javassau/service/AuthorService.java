package com.shpyakin.javassau.service;

import com.shpyakin.javassau.model.Author;
import com.shpyakin.javassau.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    
    private final AuthorRepository authorRepository;

    @Transactional
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional
    public Author getById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    @Transactional
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Transactional
    public void update(Author author) {
        authorRepository.save(author);
    }

    @Transactional
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
