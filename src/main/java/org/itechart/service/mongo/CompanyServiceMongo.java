package org.itechart.service.mongo;

import org.itechart.entity.mongo.company.Company;
import org.itechart.entity.mongo.company.CompanyType;
import org.springframework.data.domain.Page;

public interface CompanyServiceMongo {
    void update(Company company);

    void save(Company company);

    Company findOne(Long id);

    Page<Company> findAll(CompanyType companyType, int page, int pageSize);
}
