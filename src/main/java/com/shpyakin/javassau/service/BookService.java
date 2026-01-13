package com.shpyakin.javassau.service;

import com.shpyakin.javassau.entity.Book;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookService {

    @PersistenceContext
    private EntityManager entityManager;

    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> getAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public Book update(Book book) {
        return entityManager.merge(book);
    }

    public void delete(Long id) {
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            entityManager.remove(book);
        }
    }
}