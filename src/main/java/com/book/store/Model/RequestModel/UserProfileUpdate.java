package com.book.store.Model.RequestModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserProfileUpdate {
    String firstName;
    String lastName;
    String username;
    String imageId;
    String phone;
}
