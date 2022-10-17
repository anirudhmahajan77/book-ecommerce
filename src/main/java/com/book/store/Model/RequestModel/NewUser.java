package com.book.store.Model.RequestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUser {
    String username;
    String password;
    Set<Long> authorities;
    String phone;
    String imageId;
}
