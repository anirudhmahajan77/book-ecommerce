package com.book.store.Model.RequestModel;

import com.book.store.Model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddBook {
    private String name;
    private float price;
    private int stock;
    private String description;
    private float rating;
    private boolean favorite;
    private Date published;
    Genre genre;
    Long authorId;
    private String imageId;
}
