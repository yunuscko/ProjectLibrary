    package com.example.project2.entity;

    import lombok.Data;

    import javax.persistence.*;
    import java.util.Date;

    @Entity
    @Data
    @Table(name = "books")
    public class Books {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        @ManyToOne
        private Authors authors;

        @ManyToOne
        private Category category;

        private Date publishDate;

        private boolean borrowed;

        private boolean available;
    }
