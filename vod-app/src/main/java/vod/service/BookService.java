package vod.service;

import vod.model.Author;
import vod.model.Book;

import java.util.List;

public interface BookService {


    List<Book> getAllBooks();

    List<Book> getBooksByAuthor(Author d);

    Book getBookById(int id);

    Book addBook(Book m);


    List<Author> getAllAuthors();

    Author getAuthorById(int id);

    Author addAuthor(Author d);
}
