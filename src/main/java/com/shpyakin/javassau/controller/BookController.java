package com.shpyakin.javassau.controller;

import com.shpyakin.javassau.model.Book;
import com.shpyakin.javassau.service.AuthorService;
import com.shpyakin.javassau.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "book/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAll());
        return "book/form";
    }

    @PostMapping
    public String save(@ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAll());
            return "book/form";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getById(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAll());
        return "book/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("book") Book book,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAll());
            return "book/form";
        }
        book.setId(id);
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
