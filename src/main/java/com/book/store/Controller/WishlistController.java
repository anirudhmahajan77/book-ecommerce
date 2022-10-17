package com.book.store.Controller;

import com.book.store.Model.Book;
import com.book.store.Model.RequestModel.AddToCart;
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
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "Wishlist Controller", description = "This is a controller for WIshlist of Users and their CRUD operations")
public class WishlistController {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @PostMapping(value = "/add/{id}")
    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @Operation(summary = "Add Book To Wishlist",
            description = "Add new book to wishlist of the user")
    public ResponseEntity addBookToWishlist(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.addBookToWishlist(id, token);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @GetMapping()
    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @Operation(summary = "Get Wishlist",
            description = "Get all the books from wishlist of the user")
    public ResponseEntity getWishlist(HttpServletRequest request,
                                      HttpServletRequest response) {
        String token = request.getHeader("Authorization").substring(7);
        List<Book> result = customUserDetailService.getWishlist(token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @Operation(summary = "Remove Book from Wishlist",
            description = "Remove the book given id from wishlist of the user")
    public ResponseEntity deleteBookFromWishlist(@PathVariable("id") Long id,
                                                 HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.deleteBookFromWishlist(id, token);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @PostMapping("/move/")
    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @Operation(summary = "Move From Wishlist to Cart",
            description = "Move the book with given ID from the " +
                    "wishlist of the user to their cart")
    public ResponseEntity moveWishlistToCart(@RequestBody AddToCart newCart,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.moveWishlistToCart(newCart, token);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

}
