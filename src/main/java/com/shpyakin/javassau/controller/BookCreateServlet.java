package com.shpyakin.javassau.controller;

import com.shpyakin.javassau.entity.Author;
import com.shpyakin.javassau.service.AuthorService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/books/new")
public class BookCreateServlet extends HttpServlet {

    @EJB
    private AuthorService authorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorService.getAll();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/create-book.jsp").forward(req, resp);
    }
}
