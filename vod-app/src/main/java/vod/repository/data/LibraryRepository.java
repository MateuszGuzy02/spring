package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vod.model.Book;
import vod.model.Library;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    List<Library> findAllByNameContaining(String name);

    @Query("select l from Library l inner join l.books book where book=:book")
    List<Library> findAllByBooks(@Param("book") Book book);
}
