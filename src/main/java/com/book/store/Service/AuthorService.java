package com.book.store.Service;

import com.book.store.Model.Author;
import com.book.store.Model.RequestModel.AddAuthor;
import com.book.store.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    public Long addAuthor(AddAuthor addAuthor) {
        Author author = Author.builder()
                .firstname(addAuthor.getFirstname())
                .lastname(addAuthor.getLastname())
                .bio(addAuthor.getBio())
                .birth(addAuthor.getBirth())
                .death(addAuthor.getDeath())
                .imageId(addAuthor.getImageId())
                .build();
        authorRepository.save(author);
        return author.getId();
    }

    @Transactional
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @Transactional
    public Author getAuthorById(Long id){
        return authorRepository.findById(id).get();
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }
}
