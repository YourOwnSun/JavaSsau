package com.shpyakin.javassau.service;

import com.shpyakin.javassau.mappers.AuthorMapper;
import com.shpyakin.javassau.model.Author;
import com.shpyakin.javassau.model.AuthorDTO;
import com.shpyakin.javassau.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.shpyakin.javassau.model.ChangeType.*;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuditService auditService;
    private final EmailService emailService;

    @Transactional
    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AuthorDTO getById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public AuthorDTO create(AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);

        auditService.logEvent("Author", savedAuthor.getId(), CREATE, "admin");

        emailService.sendEmail(
                "andreyshpyakin1410@gmail.com",
                "New Author Created",
                "Author " + savedAuthor.getFullName() + " has been created."
        );

        return authorMapper.toDTO(savedAuthor);
    }

    @Transactional
    public AuthorDTO update(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setBirthDate(authorDTO.getBirthDate());
        author.setNationality(authorDTO.getNationality());

        Author updatedAuthor = authorRepository.save(author);

        auditService.logEvent("Author", updatedAuthor.getId(), UPDATE, "admin");

        emailService.sendEmail(
                "andreyshpyakin1410@gmail.com",
                "Author Updated",
                "Author " + updatedAuthor.getFullName() + " has been updated."
        );

        return authorMapper.toDTO(updatedAuthor);
    }

    @Transactional
    public void delete(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.delete(author);

        auditService.logEvent("Author", id, DELETE, "admin");

        emailService.sendEmail(
                "andreyshpyakin1410@gmail.com",
                "Author Deleted",
                "Author " + author.getFullName() + " has been deleted."
        );
    }
}