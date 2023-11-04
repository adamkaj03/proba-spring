package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.OrderedBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedBookRepository extends CrudRepository<OrderedBook, Integer> {
}
