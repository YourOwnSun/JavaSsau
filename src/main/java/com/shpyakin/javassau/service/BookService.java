package com.shpyakin.javassau.service;

import com.shpyakin.javassau.model.Author;
import com.shpyakin.javassau.model.Book;
import com.shpyakin.javassau.repository.AuthorRepository;
import com.shpyakin.javassau.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void saveBook(Book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Optional<Author> author = authorRepository.findById(book.getAuthor().getId());
            author.ifPresent(book::setAuthor);
        }
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Optional<Author> author = authorRepository.findById(book.getAuthor().getId());
            author.ifPresent(book::setAuthor);
        }
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
