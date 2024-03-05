    package com.example.project2.entity;

    import jakarta.persistence.*;
    import lombok.Data;
    import org.hibernate.annotations.ManyToAny;

    import java.util.Date;

    @Data
    @Entity
    @Table(name = "books")
    public class Books {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "book_id")
        private Long id;

        private String title;

        @ManyToOne
        @JoinColumn(name = "author_id")
        private Authors authors;

        @ManyToOne
        @JoinColumn(name = "category_id")
        private Category categoryId;


        private Date publishDate;

        @Column(name = "is_available", columnDefinition = "boolean default true")
        private boolean isAvailable;
    }
