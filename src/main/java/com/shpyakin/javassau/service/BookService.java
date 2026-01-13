package com.shpyakin.javassau.service;

import com.shpyakin.javassau.mappers.BookMapper;
import com.shpyakin.javassau.model.Book;
import com.shpyakin.javassau.model.BookDTO;
import com.shpyakin.javassau.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.shpyakin.javassau.model.ChangeType.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuditService auditService;
    private final EmailService emailService;

    @Transactional
    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookDTO getById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public BookDTO create(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);

        auditService.logEvent("Book", savedBook.getId(), CREATE, "admin");

        emailService.sendEmail(
                "andreyshpyakin1410@gmail.com",
                "New Book Created",
                "Book " + savedBook.getTitle() + " has been created."
        );

        return bookMapper.toDTO(savedBook);
    }

    @Transactional
    public BookDTO update(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setAvailableCopies(bookDTO.getAvailableCopies());

        Book updatedBook = bookRepository.save(book);

        auditService.logEvent("Book", updatedBook.getId(), UPDATE, "admin");

        emailService.sendEmail(
                "andreyshpyakin1410@gmail.com",
                "Book Updated",
                "Book " + updatedBook.getTitle() + " has been updated."
        );

        return bookMapper.toDTO(updatedBook);
    }

    @Transactional
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);

        auditService.logEvent("Book", id, DELETE, "admin");

        emailService.sendEmail(
                "andreyshpyakin1410@gmail.com",
                "Book Deleted",
                "Book " + book.getTitle() + " has been deleted."
        );
    }
}