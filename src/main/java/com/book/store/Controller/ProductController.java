package com.book.store.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    @RequestMapping(value = "/allproducts", method = RequestMethod.GET)
    @ApiOperation(value = "Get All Products",
            notes = "Get the List of all Products Available",
            response = ResponseEntity.class)
    public ResponseEntity getAllProducts() {

        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
