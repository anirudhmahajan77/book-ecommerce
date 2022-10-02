package com.book.store.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Product {
    @GetMapping("/all")
    public ResponseEntity getAllProducts() {

        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
