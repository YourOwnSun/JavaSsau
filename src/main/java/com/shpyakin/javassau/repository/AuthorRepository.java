package com.shpyakin.javassau.repository;

import com.shpyakin.javassau.model.Author;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
