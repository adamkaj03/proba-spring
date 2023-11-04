package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.Order;
import com.adam.buzas.onlab.main.model.ShippingType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingTypeRepository extends CrudRepository<ShippingType, Integer> {
}
