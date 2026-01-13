package com.shpyakin.javassau.service;

import com.shpyakin.javassau.model.Book;
import com.shpyakin.javassau.model.BookDTO;
import com.shpyakin.javassau.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
            .map(BookDTO::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public BookDTO getById(Long id) {
        return bookRepository.findById(id)
            .map(BookDTO::new)
            .orElse(null);
    }

    @Transactional
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setAvailableCopies(bookDetails.getAvailableCopies());
        book.setAuthor(bookDetails.getAuthor());

        return bookRepository.save(book);
    }

    @Transactional
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
}