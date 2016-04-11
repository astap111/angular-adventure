package org.itechart.service;

import org.itechart.entity.company.Company;
import org.springframework.data.domain.Page;

public interface CompanyService {
    void update(Company company);

    void save(Company company);

    Company findOne(Long id);

    Page<Company> findAll(int page, int pageSize);
}
