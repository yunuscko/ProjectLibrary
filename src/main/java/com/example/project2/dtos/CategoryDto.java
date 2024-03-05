package com.example.project2.dtos;

import com.example.project2.entity.Books;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Data
public class CategoryDto {
    private Long id;

    private String categoryName;

    @OneToMany
    private Set<Books> categoriesOfBooks;
}
