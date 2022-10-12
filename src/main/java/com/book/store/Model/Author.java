package com.book.store.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
@Builder
public class Author {
    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    @Column(name = "author_id")
    private Long id;
    private String firstname;
    private String lastname;
    private Date birth;
    private Date death;
    private String bio;
    private String imageId;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "author_book_id")
    private List<Book> books;
}
