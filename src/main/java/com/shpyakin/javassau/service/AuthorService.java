package com.shpyakin.javassau.service;

import com.shpyakin.javassau.entity.Author;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AuthorService {

    @PersistenceContext
    private EntityManager entityManager;

    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> getAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    public Author update(Author author) {
        return entityManager.merge(author);
    }

    public void delete(Long id) {
        Author author = entityManager.find(Author.class, id);
        if (author != null) {
            entityManager.remove(author);
        }
    }
}