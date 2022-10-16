package com.book.store.Controller;

import com.book.store.Model.Author;
import com.book.store.Model.Book;
import com.book.store.Model.RequestModel.AddAuthor;
import com.book.store.Service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/author")
@Tag(name = "Author Controller", description = "This is a controller for Authors of Books and their CRUD operations")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping(value = "/addauthor")
    @RolesAllowed("ADMIN")
    @Operation(summary = "Add New Author",
            description = "Add new author to store and make it available")
    public ResponseEntity addAuthor(@RequestBody AddAuthor addAuthor) {
        Long result = authorService.addAuthor(addAuthor);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Get All Author", description = "Get All the listed Authors on the store")
    public ResponseEntity getAllAuthor() {
        List<Author> result = authorService.getAllAuthors();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get Author By ID", description = "Get the Authors on the basis of their ID")
    public ResponseEntity getAuthorById(@PathVariable("id") Long id) {
        Author result = authorService.getAuthorById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @RolesAllowed("ADMIN")
    @Operation(summary = "Update Author By Id", description = "Get Author Profile and update it with new data")
    public ResponseEntity updateBook(@RequestBody Author author) {
        Author result = authorService.updateAuthor(author);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @RolesAllowed("ADMIN")
    @Operation(summary = "Delete Author By ID", description = "Delete the Author Profile on the basis of their ID")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok().build();
    }

}
