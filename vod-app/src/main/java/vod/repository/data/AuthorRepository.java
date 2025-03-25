package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
