package org.itechart.repository.mongo;

import org.itechart.entity.mongo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Long> {
}
