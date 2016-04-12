package org.itechart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CompanyBaseRepository<T> extends CrudRepository<T, Long> {

    Page<T> findAll(Pageable pageable);
}