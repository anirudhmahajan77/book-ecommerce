package com.book.store.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table
public class Order {
    /*@Id
    @Column(name = "userorder_id")
    @SequenceGenerator(
            name = "orderhistory_sequence",
            sequenceName = "orderhistory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderhistory_sequence"
    )
    Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_orderproducthistory",
            joinColumns = @JoinColumn(name = "userorder_id"),
            inverseJoinColumns = @JoinColumn(name = "orderproduct_id")
    )
    private List<OrderProduct> orderProducts;

    float bill;
    Date orderDate;*/

}
