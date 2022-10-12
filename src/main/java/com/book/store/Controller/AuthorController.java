package com.book.store.Controller;

import com.book.store.Model.Author;
import com.book.store.Model.RequestModel.AddAuthor;
import com.book.store.Service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Tag(name = "Author Controller", description = "This is a controller for Authors of Books and their CRUD operations")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping(value = "/addauthor")
    @Operation(summary = "Add New Author",
            description = "Add new author to store and make it available")
    public ResponseEntity addAuthor(@RequestBody AddAuthor addAuthor) {
        Long result = authorService.addAuthor(addAuthor);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/allauthors")
    @Operation(summary = "Get All Author", description = "Get All the listed Authors on the store")
    public ResponseEntity getAllAuthor() {
        List<Author> result = authorService.getAllAuthors();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/findauthor/{id}")
    @Operation(summary = "Get Author By ID", description = "Get the Authors on the basis of their ID")
    public ResponseEntity getAuthorById(@PathVariable("id") Long id) {
        Author result = authorService.getAuthorById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
