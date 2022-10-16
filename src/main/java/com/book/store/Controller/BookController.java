package com.book.store.Controller;

import com.book.store.Model.Book;
import com.book.store.Model.Genre;
import com.book.store.Model.RequestModel.AddBook;
import com.book.store.Service.BookService;
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
@RequestMapping("/api/book")
@Tag(name = "Book Controller", description = "This is a controller for Books and it's CRUD operations")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping(value = "/add")
    @RolesAllowed("ADMIN")
    @Operation(summary = "Add New Book", description = "Add new book to store and make it available")
    public ResponseEntity addBook(@RequestBody AddBook addBook) {
        Long result = bookService.addBook(addBook);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    @Operation(summary = "Find Book By Id",
            description = "Find the Book using Id and return the response of a single book")
    public ResponseEntity findBook(@PathVariable Long id) {
        Book result = bookService.getBookById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "author/{id}")
    @Operation(summary = "Find Book By Author Id",
            description = "Find the Book using Author Id and return the response list")
    public ResponseEntity findBookByAuthorId(@PathVariable Long id) {
        List<Book> result = bookService.getBookByAuthorId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/genre/{genre}")
    @Operation(summary = "Get Books By Genre",
            description = "Get Existing Book using their Genre")
    public ResponseEntity getBooksByGenre(@PathVariable Genre genre) {
        List<Book> result = bookService.findByGenre(genre);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/favorite")
    @Operation(summary = "Get Favorite Books", description = "Get Existing Favorite Book")
    public ResponseEntity getfavoriteBooks() {
        List<Book> result = bookService.findFavorite();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Get All Books", description = "Get the List of all Books Available")
    public ResponseEntity getAllBooks() {
        List<Book> result = bookService.getAllBooks();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @RolesAllowed("ADMIN")
    @Operation(summary = "Update Book By Id", description = "Get Book and update it with new data")
    public ResponseEntity updateBook(@RequestBody Book book) {
        Book result = bookService.updateBook(book);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletebook/{id}")
    @RolesAllowed("ADMIN")
    @Operation(summary = "Delete Book By Id", description = "Delete Existing Book using the ID")
    public ResponseEntity updateBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>("Book Deleted!", HttpStatus.OK);
    }
}
