package org.itechart.service.mongo;

import org.itechart.entity.mongo.Product;

import java.util.List;

public interface ProductService {
    void saveProducts(List<Product> products);
}
