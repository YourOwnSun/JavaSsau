package com.shpyakin.javassau.controller;

import com.shpyakin.javassau.entity.Author;
import com.shpyakin.javassau.entity.Book;
import com.shpyakin.javassau.service.AuthorService;
import com.shpyakin.javassau.service.BookService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    @EJB
    private BookService bookService;

    @EJB
    private AuthorService authorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/book-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");
        int publicationYear = Integer.parseInt(req.getParameter("publicationYear"));
        int availableCopies = Integer.parseInt(req.getParameter("availableCopies"));
        long authorId = Long.parseLong(req.getParameter("authorId"));

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationYear(publicationYear);
        book.setAvailableCopies(availableCopies);

        // Load the actual Author entity from database
        Author author = authorService.findById(authorId);
        book.setAuthor(author);  // Now it's a managed entity

        bookService.create(book);
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}