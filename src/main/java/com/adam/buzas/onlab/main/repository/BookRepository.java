package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.Category;
import com.adam.buzas.onlab.main.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    public Iterable<Book> findKonyvByCategory(Category category);

    public Optional<Book> findByTitle(String title);

    public Optional<Book> findByTitleAndAuthor(String title, String author);

    public Iterable<Book> findByTitleStartingWithOrAuthorStartingWith(String title, String author);
}
