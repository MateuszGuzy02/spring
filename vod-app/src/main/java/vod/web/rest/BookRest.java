package vod.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vod.model.Book;
import vod.model.Library;
import vod.service.BookService;
import vod.service.LibraryService;
import vod.web.rest.dto.BookDto;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class BookRest {
    private final LibraryService libraryService;
    private final BookService bookService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final BookValidator bookValidator;

//    @InitBinder
//    void initBinder(WebDataBinder binder) { binder.addValidators(bookValidator); }


    @GetMapping("/books")
    List<Book> getBooks() {
        log.info("about to retrieve books list");
        List<Book> books = bookService.getAllBooks();
        log.info("{} books found", books.size());
        return books;
    }

    @GetMapping("/books/{id}")
    ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        log.info("about to retrieve book {}", id);
        Book book = bookService.getBookById(id);
        log.info("{} book found", book);
        if(book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/libraries/{libraryId}/books")
    ResponseEntity<List<Book>> getBooksInLibrary(@PathVariable("libraryId") int libraryId) {
        log.info("about to retrieve books in library {}", libraryId);
        Library library = libraryService.getLibraryById(libraryId);
        if(library == null) {
            return ResponseEntity.notFound().build();
        } else {
            List<Book> books = libraryService.getBooksInLibrary(library);
            log.info("there's {} books in library {}", books.size(), library.getName());
            return ResponseEntity.ok(books);
        }
    }

    @PostMapping("/books")
    ResponseEntity<?> addBook(@Validated @RequestBody BookDto bookDto, Errors errors, HttpServletRequest request) {
        log.info("about to add new book {}", bookDto);

        if(errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorsMessage = errors.getAllErrors().stream()
                    .map(oe->messageSource.getMessage(oe.getCode(), new Object[0], locale))
                    .reduce("errors:\n", (accu, oe)->accu + oe + "\n");
            return ResponseEntity.badRequest().body(errorsMessage);
        }

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setPoster(bookDto.getPoster());
        book.setRating(bookDto.getRating());
        book.setAuthor(bookService.getAuthorById(bookDto.getAuthorId()));

        book = bookService.addBook(book);
        log.info("new book added: {}", book);

        //return ResponseEntity.status(HttpStatus.CREATED).body(book);

        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequestUri()
                                .path("/" + book.getId())
                                .build()
                                .toUri())
                .body(book);
    }
}
