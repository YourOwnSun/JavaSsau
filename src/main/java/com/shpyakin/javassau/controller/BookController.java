package com.shpyakin.javassau.controller;

import com.shpyakin.javassau.model.Book;
import com.shpyakin.javassau.model.BookDTO;
import com.shpyakin.javassau.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
        BookDTO book = bookService.getById(id);
        return book != null
            ? ResponseEntity.ok(book)
            : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.update(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}