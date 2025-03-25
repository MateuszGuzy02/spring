package vod.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import vod.repository.LibraryDao;
import vod.repository.AuthorDao;
import vod.repository.BookDao;
import vod.model.Library;
import vod.model.Author;
import vod.model.Book;
import vod.service.BookService;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class BookServiceBean implements BookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());

    private final AuthorDao authorDao;
    private final LibraryDao libraryDao;
    private final BookDao bookDao;
    private final PlatformTransactionManager transactionManager;


    public List<Book> getAllBooks() {
        log.info("searching all books...");
        return bookDao.findAll();
    }

    public List<Book> getBooksByAuthor(Author a) {
        log.info("searching books by author " + a.getId());
        return bookDao.findByAuthor(a);
    }

    public List<Book> getBooksInLibrary(Library l) {
        log.info("searching books available in library " + l.getId());
        return bookDao.findByLibrary(l);
    }

    public Book getBookById(int id) {
        log.info("searching book by id " + id);
        return bookDao.findById(id);
    }

    public List<Library> getAllLibraries() {
        log.info("searching all libraries");
        return libraryDao.findAll();
    }

    public List<Library> getLibrariesByBook(Book b) {
        log.info("searching libraries by book " + b.getId());
        return libraryDao.findByBook(b);
    }

    public Library getLibraryById(int id) {
        log.info("searching library by id " + id);
        return libraryDao.findById(id);
    }

    public List<Author> getAllAuthors() {
        log.info("searching all authors");
        return authorDao.findAll();
    }

    public Author getAuthorById(int id) {
        log.info("searching author by id " + id);
        return authorDao.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Book addBook(Book b) {
        log.info("about to add book " + b);
        TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            b = bookDao.add(b);
            if(b.getTitle().equals("Apocalypse Now")) {
                throw new RuntimeException("not yet!");
            }
            transactionManager.commit(ts);
        } catch (RuntimeException e) {
            transactionManager.rollback(ts);
            throw e;
        }

        return b;
    }

    @Override
    public Author addAuthor(Author a) {
        log.info("about to add author " + a);
        return authorDao.add(a);
    }

}
