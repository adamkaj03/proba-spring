package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
