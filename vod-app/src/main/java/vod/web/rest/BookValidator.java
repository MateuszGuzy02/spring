package vod.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.Author;
import vod.service.BookService;
import vod.web.rest.dto.BookDto;

@Component
@RequiredArgsConstructor
public class BookValidator implements Validator {

    private final BookService bookService;

    @Override
    public boolean supports(Class<?> clazz) { return clazz.isAssignableFrom(BookDto.class); }

    @Override
    public void validate(Object target, Errors errors) {
        BookDto book = (BookDto) target;
        Author author = bookService.getAuthorById(book.getAuthorId());

        if(author == null) {
            errors.rejectValue("authorId", "book.author.missing");
        }
    }
}
