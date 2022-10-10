package com.book.store.Controller;

import com.book.store.Model.Book;
import com.book.store.Model.Genre;
import com.book.store.Model.RequestModel.AddBook;
import com.book.store.Service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping(value = "/addbook")
    @ApiOperation(value = "Add New Book",
            notes = "Add new book to store and make it available",
            response = ResponseEntity.class)
    public ResponseEntity addBook(@RequestBody AddBook addBook) {
        Long result = bookService.addBook(addBook);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "findbook/{id}")
    @ApiOperation(value = "Find Book By Id",
            notes = "Find the Book using Id and return the response of a single book",
            response = ResponseEntity.class)
    public ResponseEntity findBook(@PathVariable Long id) {
        Book result = bookService.getBookById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "findbookbyauthor/{id}")
    @ApiOperation(value = "Find Book By Author Id",
            notes = "Find the Book using Author Id and return the response list",
            response = ResponseEntity.class)
    public ResponseEntity findBookByAuthorId(@PathVariable Long id) {
        List<Book> result = bookService.getBookByAuthorId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/findbygenre/{genre}")
    @ApiOperation(value ="Get Books By Genre",
            notes = "Get Existing Book using their Genre",
            response = ResponseEntity.class)
    public ResponseEntity getBooksByGenre(@PathVariable Genre genre) {
        List<Book> result = bookService.findByGenre(genre);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/findfavorite")
    @ApiOperation(value ="Get Favorite Books",
            notes = "Get Existing Favorite Book",
            response = ResponseEntity.class)
    public ResponseEntity getfavoriteBooks() {
        List<Book> result = bookService.findFavorite();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/allbooks")
    @ApiOperation(value = "Get All Books",
            notes = "Get the List of all Books Available",
            response = ResponseEntity.class)
    public ResponseEntity getAllBooks() {
        List<Book> result = bookService.getAllBooks();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/updatebook")
    @ApiOperation(value ="Update Book By Id",
            notes = "Get Book and update it with new data",
            response = ResponseEntity.class)
    public ResponseEntity updateBook(@RequestBody Book book) {
        Book result = bookService.updateBook(book);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletebook/{id}")
    @ApiOperation(value ="Delete Book By Id",
            notes = "Delete Existing Book using the ID",
            response = ResponseEntity.class)
    public ResponseEntity updateBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>("Book Deleted!", HttpStatus.OK);
    }
}
