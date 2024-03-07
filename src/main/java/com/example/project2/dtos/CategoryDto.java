package com.example.project2.dtos;

import com.example.project2.entity.Books;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Set;

@Data
public class CategoryDto {
    private Long id;

    private String categoryName;

    @OneToMany
    private Set<Books> categoriesOfBooks;
}
