package com.shpyakin.javassau.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class BookDTO implements Serializable {
    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private Integer availableCopies;
    private Long authorId;
    private String authorFullName;

    public BookDTO(Book book) {
        if (book != null) {
            this.id = book.getId();
            this.title = book.getTitle();
            this.isbn = book.getIsbn();
            this.publicationYear = book.getPublicationYear();
            this.availableCopies = book.getAvailableCopies();
            
            // Проверка на null для автора
            if (book.getAuthor() != null) {
                this.authorId = book.getAuthor().getId();
                this.authorFullName = String.format("%s %s", 
                    book.getAuthor().getFirstName(), 
                    book.getAuthor().getLastName());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) &&
               Objects.equals(title, bookDTO.title) &&
               Objects.equals(isbn, bookDTO.isbn) &&
               Objects.equals(publicationYear, bookDTO.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, publicationYear);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", availableCopies=" + availableCopies +
                ", authorId=" + authorId +
                ", authorFullName='" + authorFullName + '\'' +
                '}';
    }
}