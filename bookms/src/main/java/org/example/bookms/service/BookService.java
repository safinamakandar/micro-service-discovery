package org.example.bookms.service;

import org.example.bookms.exception.InformationNotFoundException;
import org.example.bookms.model.AuthorDTO;
import org.example.bookms.model.Book;
import org.example.bookms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorServiceClient authorServiceClient;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorServiceClient authorServiceClient) {
        this.bookRepository = bookRepository;
        this.authorServiceClient = authorServiceClient;
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("Book with ID " + id + " not found."));
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new InformationNotFoundException("Book with ID " + id + " not found.");
        }
        bookRepository.deleteById(id);
    }

    // request to author service to get author details using feign client
    public Book saveBook(Book book) {
        AuthorDTO authorDTO = authorServiceClient.getAuthorById(book.getAuthor().getAuthorId());
        book.setAuthor(authorDTO);
        return bookRepository.save(book);
    }

    public Optional<Book> findBookByTitleAndAuthorName(String title, String authorName) {
        return bookRepository.findByTitleAndAuthor_Name(title, authorName);
    }
}
