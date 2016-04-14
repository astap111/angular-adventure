package org.itechart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyBaseRepository<T> extends JpaRepository<T, Long> {

    Page<T> findAll(Pageable pageable);
}