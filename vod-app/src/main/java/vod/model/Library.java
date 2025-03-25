package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    @Column(name = "logo")
    private String logo;

    @ManyToMany(mappedBy = "libraries")
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    public Library(int id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Library() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book m) {
        this.books.add(m);
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
