package com.shpyakin.javassau.controller;

import com.shpyakin.javassau.model.Author;
import com.shpyakin.javassau.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    
    private final AuthorService authorService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "author/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/form";
    }

    @PostMapping
    public String save(@ModelAttribute("author") Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author/form";
        }
        authorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Author author = authorService.getById(id);
        if (author == null) {
            return "redirect:/authors";
        }
        model.addAttribute("author", author);
        return "author/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("author") Author author,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "author/form";
        }
        author.setId(id);
        authorService.update(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }
}
