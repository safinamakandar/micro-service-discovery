package org.example.bookms.controller;

import org.example.bookms.model.AuthorDTO;
import org.example.bookms.model.Book;
import org.example.bookms.service.BookService;
import org.example.bookms.service.AuthorServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorServiceClient authorServiceClient; // Use a client to communicate with the Author microservice

    @Autowired
    public BookController(BookService bookService, AuthorServiceClient authorServiceClient) {
        this.bookService = bookService;
        this.authorServiceClient = authorServiceClient;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Long authorId = book.getAuthor().getAuthorId();
        AuthorDTO authorDTO = authorServiceClient.getAuthorById(authorId);
        book.setAuthor(authorDTO);

        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Book> getBookByTitleAndAuthor(@RequestParam String title, @RequestParam String authorName) {
        Optional<Book> book = bookService.findBookByTitleAndAuthorName(title, authorName);
        return book.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
