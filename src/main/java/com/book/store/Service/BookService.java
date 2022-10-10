package com.book.store.Service;

import com.book.store.Model.Author;
import com.book.store.Model.Book;
import com.book.store.Model.Genre;
import com.book.store.Model.RequestModel.AddBook;
import com.book.store.Repository.AuthorRepository;
import com.book.store.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    public Long addBook(AddBook addBook){
        Author author = authorRepository.findById(addBook.getAuthorId()).get();


        Book book = Book.builder()
                .name(addBook.getName())
                .description(addBook.getDescription())
                .stock(addBook.getStock())
                .price(addBook.getPrice())
                .rating(addBook.getRating())
                .genre(addBook.getGenre())
                .favorite(addBook.isFavorite())
                .published(addBook.getPublished())
                .authorId(addBook.getAuthorId())
                .build();
        bookRepository.save(book);
        List<Book> books = author.getBooks();
        books.add(book);
        author.setBooks(books);
        authorRepository.save(author);
        return book.getId();
    }

    @Transactional
    public Book getBookById(Long id){
        return bookRepository.findById(id).get();
    }

    @Transactional
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    @Modifying
    public Book updateBook(Book newBook) {
        bookRepository.save(newBook);
        return bookRepository.findById(newBook.getId()).get();
    }

    @Transactional
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public List<Book> findByGenre(Genre genre) {
        return bookRepository.findBookByGenre(genre);
    }

    @Transactional
    public List<Book> findFavorite() {
        return bookRepository.findBookByFavoriteIsTrue();
    }

    public List<Book> getBookByAuthorId(Long id) {
        return bookRepository.findBookByAuthorId(id);
    }
}
