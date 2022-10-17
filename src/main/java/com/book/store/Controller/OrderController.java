package com.book.store.Controller;

import com.book.store.Service.CustomUserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/order")
@Tag(name = "Order Controller", description = "This is a controller to place order of Users and their CRUD operations")

public class OrderController {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @PostMapping("/place/{id}")
    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @Operation(summary = "Add Book To Wishlist",
            description = "Add new book to wishlist of the user")
    public ResponseEntity placeOrder(
            @PathVariable("id") Long id
            ,HttpServletRequest request,
             HttpServletResponse response) {
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.placeOrder(id, token);
        return new ResponseEntity<>("Order Placed!", HttpStatus.OK);
    }

}
