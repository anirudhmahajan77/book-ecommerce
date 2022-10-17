package com.book.store.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class OrderProduct {
    @Id
    @SequenceGenerator(
            name = "orderproduct_sequence",
            sequenceName = "orderproduct_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderproduct_sequence"
    )
    @Column(name = "orderproduct_id")
    Long id;

    long bookId;
    int quantity;
}
