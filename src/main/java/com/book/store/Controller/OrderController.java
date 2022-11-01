package com.book.store.Controller;

import com.book.store.Model.RequestModel.UpdateOrderState;
import com.book.store.Service.CustomUserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/order")
@Tag(name = "Order Controller", description = "This is a controller to place order of Users and their CRUD operations")

public class OrderController {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @PostMapping("/place/{id}")
    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @Operation(summary = "Place Order",
            description = "Place User Order")
    public ResponseEntity placeOrder(
            @PathVariable("id") Long id
            ,HttpServletRequest request,
             HttpServletResponse response) {
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.placeOrder(id, token);
        return new ResponseEntity<>("Order Placed!", HttpStatus.OK);
    }

    @GetMapping("/update")
    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @Operation(summary = "Add Book To Wishlist",
            description = "Add new book to wishlist of the user")
    public ResponseEntity updateOrderState(
            @RequestBody UpdateOrderState newState
            , HttpServletRequest request,
            HttpServletResponse response) {
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.updateOrderState(newState, token);
        return new ResponseEntity<>("Order Placed!", HttpStatus.OK);
    }



}
