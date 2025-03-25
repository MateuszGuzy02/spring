package vod.repository;

import vod.model.Library;
import vod.model.Author;
import vod.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    Book findById(int id);

    List<Book> findByAuthor(Author d);

    List<Book> findByLibrary(Library c);

    Book add(Book m);

}
