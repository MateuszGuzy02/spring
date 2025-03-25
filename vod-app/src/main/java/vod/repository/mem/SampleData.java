package vod.repository.mem;

import vod.model.Library;
import vod.model.Author;
import vod.model.Book;

import java.util.ArrayList;
import java.util.List;

class SampleData {

    static List<Library> libraries = new ArrayList<>();

    static List<Author> authors = new ArrayList<>();

    static List<Book> books = new ArrayList<>();

    static {

        // Autorzy
        Author rowling = new Author(1, "J.K.", "Rowling");
        Author tolkien = new Author(2, "J.R.R.", "Tolkien");
        Author king = new Author(3, "Stephen", "King");
        Author sapkowski = new Author(4, "Andrzej", "Sapkowski");

        // Książki
        Book harryPotter = new Book(1, "Harry Potter i Kamień Filozoficzny",
                "https://ecsmedia.pl/c/harry-potter-i-kamien-filozoficzny-tom-1-b-iext115254192.jpg",
                rowling, (float) 4.8);
        Book fantasticBeasts = new Book(2, "Fantastyczne zwierzęta i jak je znaleźć",
                "https://ecsmedia.pl/c/fantastyczne-zwierzeta-i-jak-je-znalezc-b-iext118060420.jpg",
                rowling, (float) 4.3);

        Book lordOfTheRings = new Book(3, "Władca Pierścieni: Drużyna Pierścienia",
                "https://ecsmedia.pl/c/wladca-pierscieni-druzyna-pierscienia-b-iext43066090.jpg",
                tolkien, (float) 4.9);
        Book hobbit = new Book(4, "Hobbit",
                "https://ecsmedia.pl/c/hobbit-b-iext120304327.jpg",
                tolkien, (float) 4.7);

        Book shining = new Book(5, "Lśnienie",
                "https://ecsmedia.pl/c/lsnienie-b-iext120953892.jpg",
                king, (float) 4.5);
        Book misery = new Book(6, "Misery",
                "https://ecsmedia.pl/c/misery-b-iext120953397.jpg",
                king, (float) 4.4);

        Book wiedzmin = new Book(7, "Wiedźmin: Ostatnie życzenie",
                "https://ecsmedia.pl/c/ostatnie-zyczenie-b-iext120042534.jpg",
                sapkowski, (float) 4.8);
        Book krew = new Book(8, "Krew elfów",
                "https://ecsmedia.pl/c/wiedzmin-3-krew-elfow-b-iext120042874.jpg",
                sapkowski, (float) 4.7);

        // Relacje książka-autor
        bind(harryPotter, rowling);
        bind(fantasticBeasts, rowling);

        bind(lordOfTheRings, tolkien);
        bind(hobbit, tolkien);

        bind(shining, king);
        bind(misery, king);

        bind(wiedzmin, sapkowski);
        bind(krew, sapkowski);

        // Biblioteki
        Library miejska = new Library(1, "Biblioteka Miejska",
                "https://biblioteka.wroc.pl/wp-content/themes/mbpwr/assets/images/logo.png");
        Library uniwersytecka = new Library(2, "Biblioteka Uniwersytecka",
                "https://bg.up.krakow.pl/wp-content/uploads/2020/04/logo-blue-1.png");
        Library pedagogiczna = new Library(3, "Biblioteka Pedagogiczna",
                "https://www.pbw.edu.pl/images/pbw/pbw-logo.png");
        Library osiedlowa = new Library(4, "Biblioteka Osiedlowa",
                "https://biblioteka.targowek.waw.pl/wp-content/uploads/2020/04/logo.png");

        // Relacje biblioteka-książka
        bind(miejska, harryPotter);
        bind(uniwersytecka, harryPotter);
        bind(uniwersytecka, lordOfTheRings);
        bind(uniwersytecka, wiedzmin);

        bind(miejska, misery);
        bind(osiedlowa, misery);
        bind(osiedlowa, shining);
        bind(pedagogiczna, shining);
        bind(pedagogiczna, hobbit);

        // Dodawanie do list
        books.add(harryPotter);
        books.add(fantasticBeasts);
        books.add(lordOfTheRings);
        books.add(hobbit);
        books.add(shining);
        books.add(misery);
        books.add(wiedzmin);
        books.add(krew);

        authors.add(rowling);
        authors.add(tolkien);
        authors.add(king);
        authors.add(sapkowski);

        libraries.add(miejska);
        libraries.add(uniwersytecka);
        libraries.add(pedagogiczna);
        libraries.add(osiedlowa);

    }

    private static void bind(Library c, Book m) {
        c.addBook(m);
        m.addLibrary(c);
    }

    private static void bind(Book m, Author d) {
        d.addBook(m);
        m.setAuthor(d);
    }

}
