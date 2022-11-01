package com.book.store.Model.RequestModel;

import com.book.store.Model.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderState {
    Long id;
    OrderState newState;
}
