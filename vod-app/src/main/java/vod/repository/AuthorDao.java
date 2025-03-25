package vod.repository;

import vod.model.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> findAll();

    Author findById(int id);

    Author add(Author d);


}
