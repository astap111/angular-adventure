package org.itechart.service;

import org.itechart.entity.company.Company;
import org.itechart.entity.company.CompanyType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    void update(Company company);

    void save(Company company);

    Company findOne(Long id);

    Page<? extends Company> findAll(CompanyType companyType, int page, int pageSize);

    List<? extends Company> findAll(CompanyType companyType);
}
