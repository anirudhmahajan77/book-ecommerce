package com.book.store.Repository;

import com.book.store.Model.Book;
import com.book.store.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByGenre(Genre genre);
    List<Book> findFirst3BookByNameContainingIgnoreCase(String name);
    List<Book> findBookByFavoriteIsTrue();
    List<Book> findBookByAuthorId(Long id);
}
