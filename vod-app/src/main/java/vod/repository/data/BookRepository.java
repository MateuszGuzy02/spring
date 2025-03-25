package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Author;
import vod.model.Book;
import vod.model.Library;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthor(Author a);

    List<Book> findAllByLibrariesContaining(Library l);
}
