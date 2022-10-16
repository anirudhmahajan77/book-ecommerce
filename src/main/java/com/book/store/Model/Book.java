package com.book.store.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "book_id")
    private Long id;
    private String name;
    private float price;
    private int stock;
    private String description;
    private float rating;
    boolean favorite;
    private Date published;
    private Long authorId;
    private String imageId;
    private float discount;

    @Enumerated(EnumType.STRING)
    Genre genre;



}
