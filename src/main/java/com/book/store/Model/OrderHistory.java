package com.book.store.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class OrderHistory {

    @Id
    @SequenceGenerator(
            name = "history_sequence",
            sequenceName = "history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "history_sequence"
    )
    @Column(name = "dummy_id")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_holoid",
            joinColumns = @JoinColumn(name = "dummy_id"),
            inverseJoinColumns = @JoinColumn(name = "orderproduct_id")
    )
    private List<OrderProduct> orderProducts;
    float price;
    Date orderDate;
    Long addressId;

    @Enumerated(EnumType.STRING)
    OrderState orderState;
}
