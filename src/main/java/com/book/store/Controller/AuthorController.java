package com.book.store.Controller;

import com.book.store.Model.Author;
import com.book.store.Model.RequestModel.AddAuthor;
import com.book.store.Service.AuthorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping(value = "/addauthor")
    @ApiOperation(value = "Add New Author",
            notes = "Add new author to store and make it available",
            response = ResponseEntity.class)
    public ResponseEntity addAuthor(@RequestBody AddAuthor addAuthor) {
        Long result = authorService.addAuthor(addAuthor);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/allauthors")
    @ApiOperation(value = "Get All Author",
            notes = "Get All the listed Authors on the store",
            response = ResponseEntity.class)
    public ResponseEntity getAllAuthor() {
        List<Author> result = authorService.getAllAuthors();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/findauthor/{id}")
    @ApiOperation(value = "Get Author By ID",
            notes = "Get the Authors on the basis of their ID",
            response = ResponseEntity.class)
    public ResponseEntity getAuthorById(@PathVariable("id") Long id) {
        Author result = authorService.getAuthorById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
