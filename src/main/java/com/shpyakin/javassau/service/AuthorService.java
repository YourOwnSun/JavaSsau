package com.shpyakin.javassau.service;

import com.shpyakin.javassau.model.Author;
import com.shpyakin.javassau.model.AuthorDTO;
import com.shpyakin.javassau.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream()
            .map(AuthorDTO::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public AuthorDTO getById(Long id) {
        return authorRepository.findById(id)
            .map(AuthorDTO::new)
            .orElse(null);
    }

    @Transactional
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public Author update(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setFirstName(authorDetails.getFirstName());
        author.setLastName(authorDetails.getLastName());
        author.setBirthDate(authorDetails.getBirthDate());
        author.setNationality(authorDetails.getNationality());

        return authorRepository.save(author);
    }

    @Transactional
    public void delete(Long id) {
        Author author = authorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.delete(author);
    }
}