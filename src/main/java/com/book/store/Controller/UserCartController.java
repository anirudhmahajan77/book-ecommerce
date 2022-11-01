package com.book.store.Controller;

import com.book.store.Model.CartProduct;
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
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cart")
@Tag(name = "Cart Controller", description = "This is a controller for Cart of Users and their CRUD operations")
public class UserCartController {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @PostMapping(value = "/add/{id}")
    @RolesAllowed({"ADMIN","CUSTOMER"})
    @Operation(summary = "Add To Cart",
            description = "Add new book to store and make it available")
    public ResponseEntity addToCart(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.addToCart(id, token);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUserCart(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization").substring(7);
        List<CartProduct> result = customUserDetailService.getUserCart(token);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsersCarts(){
        Map<Long, List<CartProduct>> result = customUserDetailService.getAllUserCarts();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookFromCart(@PathVariable("id") Long bookId, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.deleteBookFromCart(bookId, token);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

}
