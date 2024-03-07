package com.example.project2.dtos;

import com.example.project2.entity.Authors;
import com.example.project2.entity.Books;
import com.example.project2.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;

    private String title;

    @ManyToOne
    private Authors authors;

    @ManyToOne
    private Category categoryId;

    private Date publishDate;

    private boolean isAvailable;

}
