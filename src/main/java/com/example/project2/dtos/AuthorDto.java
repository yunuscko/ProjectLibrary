package com.example.project2.dtos;

import com.example.project2.entity.Books;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Data
public class AuthorDto {
    private Long id;

    private String authorsName;

    @OneToMany
    private Set<Books> authorsOdBooks;
}
