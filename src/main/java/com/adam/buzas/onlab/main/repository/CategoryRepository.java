package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Optional<Category> findByName(String category);
}
