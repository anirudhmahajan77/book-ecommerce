package com.book.store.Model.RequestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddAuthor {
    private String firstname;
    private String lastname;
    private Date birth;
    private Date death;
    private String bio;
    private String imageId;
}
