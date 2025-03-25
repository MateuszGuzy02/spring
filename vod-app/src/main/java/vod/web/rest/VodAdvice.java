package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(basePackages = "vod.web.rest")
@RequiredArgsConstructor
@Slf4j
public class VodAdvice {
    private final LibraryValidator libraryValidator;
    private final  BookValidator bookValidator;

    @InitBinder("Library")
    void initBinderForLibrary(WebDataBinder binder) {
        binder.addValidators(libraryValidator);
    }

    @InitBinder("bookDto")
    void initBinderForBook(WebDataBinder binder) {
        binder.addValidators(bookValidator);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Illegal argument provided", e);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleExecption(Exception e) {
        log.error("generic exception", e);
        return ResponseEntity.status(HttpStatus.LOOP_DETECTED).body(e.getMessage());
    }
}
