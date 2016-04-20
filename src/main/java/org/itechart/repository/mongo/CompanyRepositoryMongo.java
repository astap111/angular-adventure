package org.itechart.repository.mongo;


import org.itechart.entity.mongo.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepositoryMongo extends MongoRepository<Company, Long> {
    Page<Company> findBy_class(String clazz, Pageable pageable);
}
