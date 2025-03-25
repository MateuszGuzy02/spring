package vod.service;

import vod.model.Library;
import vod.model.Book;

import java.util.List;

public interface LibraryService {
//api zwraca nam wszystkie biblioteki
    Library getLibraryById(int id);

    List<Library> getAllLibraries();

    List<Library> getLibrariesByBook(Book m);

    List<Book> getBooksInLibrary(Library c);

    Library addLibrary(Library library);
}
