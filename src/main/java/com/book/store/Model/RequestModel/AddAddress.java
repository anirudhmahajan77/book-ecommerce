package com.book.store.Model.RequestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAddress {
    String username;
    String title;
    String number;
    String houseNumber;
    String locality;
    String state;
    Integer pinCode;
    String country;
}
