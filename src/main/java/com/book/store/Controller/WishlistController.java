package com.book.store.Controller;

import com.book.store.Model.RequestModel.AddAuthor;
import com.book.store.Model.RequestModel.AddWishlist;
import com.book.store.Service.CustomUserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @PostMapping(value = "/add")
    @RolesAllowed({"ADMIN","CUSTOMER"})
    @Operation(summary = "Add Book To Wishlist",
            description = "Add new book to wishlist of the user")
    public ResponseEntity addBookToWishlist(@RequestBody AddWishlist addWishlist) {
        customUserDetailService.addBookToWishlist(addWishlist);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

}
