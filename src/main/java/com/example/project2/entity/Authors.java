package com.example.project2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "authors")
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorsName;

    @OneToMany(mappedBy = "authors")
    private Set<Books> authorsOfBooks;
}
