package com.book.store.Model.ResponseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HeaderMenuUserData {
    String username;
    String imageId;
    Long userId;
    String firstName;
    String lastName;
}
