package com.shpyakin.javassau.model;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String nationality;

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.birthDate = author.getBirthDate();
        this.nationality = author.getNationality();
    }
}