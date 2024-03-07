package com.example.project2.dtos;

import com.example.project2.entity.Books;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Set;

@Data
public class AuthorDto {
    private Long id;

    private String authorsName;

    @OneToMany
    private Set<Books> authorsOdBooks;
}
