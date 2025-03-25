package vod.web.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Author;
import vod.model.Book;
import vod.model.Library;
import vod.service.BookService;
import vod.service.LibraryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final LibraryService libraryService;
    private final BookService bookService;


    @GetMapping("/books")
    String getBooks(Model model,
                    @RequestParam(value = "libraryId", required = false) Integer libraryId,
                    @RequestParam(value = "authorId", required = false) Integer authorId)
    {
        log.info("about to display books list in library {}", libraryId);

        if(libraryId != null) {
            Library library = libraryService.getLibraryById(libraryId);
            List<Book> books = libraryService.getBooksInLibrary(library);

            model.addAttribute("books", books);
            model.addAttribute("title", "Books in library '" + library.getName() + "'");
        }
        else if(authorId != null) {
            Author author = bookService.getAuthorById(authorId);
            List<Book> books = bookService.getBooksByAuthor(author);

            model.addAttribute("books", books);
            model.addAttribute("title", "Books author by '" + author.getLastName() + "'");
        }
        else {
            List<Book> books = bookService.getAllBooks();

            model.addAttribute("books", books);
            model.addAttribute("title", "Books");
        }

        return "booksView";
    }
}
