package vod.repository;

import vod.model.Library;
import vod.model.Book;

import java.util.List;

public interface LibraryDao {

    List<Library> findAll();

    Library findById(int id);

    List<Library> findByBook(Book m);

    Library save(Library library);
}
