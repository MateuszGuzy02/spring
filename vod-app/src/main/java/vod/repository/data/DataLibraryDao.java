package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Book;
import vod.model.Library;
import vod.repository.LibraryDao;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataLibraryDao implements LibraryDao {

    private final LibraryRepository repository;

    @Override
    public List<Library> findAll() {
        return repository.findAll();
    }

    @Override
    public Library findById(int id) { return repository.findById(id).orElse(null); }

    @Override
    public List<Library> findByBook(Book b) { return repository.findAllByBooks(b); }

    @Override
    public Library save(Library library) { return repository.save(library); }
}
