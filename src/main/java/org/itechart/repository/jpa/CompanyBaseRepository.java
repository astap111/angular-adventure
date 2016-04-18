package org.itechart.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CompanyBaseRepository<T> extends JpaRepository<T, Long> {

    Page<T> findAll(Pageable pageable);
}