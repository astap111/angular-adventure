package org.itechart.service.mongo;

import org.itechart.entity.mongo.Product;
import org.itechart.repository.mongo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CounterService counterService;

    @Override
    public void saveProducts(List<Product> products) {
        if (CollectionUtils.isEmpty(products)) {
            throw new IllegalArgumentException("Products are empty");
        }
        for (Product product : products) {
            if (product.getId() == null) {
                product.setId(counterService.getNextSequence(Product.class.getSimpleName()));
            }
        }
        productRepository.save(products);
    }
}
