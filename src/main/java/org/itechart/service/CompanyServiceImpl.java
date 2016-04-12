package org.itechart.service;

import org.hibernate.Hibernate;
import org.itechart.entity.company.Company;
import org.itechart.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void update(Company company) {
        if (company.getId() == null) {
            throw new IllegalArgumentException("Failed to update company with id == null");
        }
        companyRepository.save(company);
    }

    @Override
    public void save(Company company) {
        if (company.getId() != null) {
            throw new IllegalArgumentException("Failed to save company with not null id");
        }
        companyRepository.save(company);
    }

    @Override
    public Company findOne(Long id) {
        Company company = companyRepository.findOne(id);
        Hibernate.initialize(company.getUsers());
        return company;
    }

    @Override
    public Page<Company> findAll(int page, int pageSize) {
        Page<Company> companiesPage = companyRepository.findAll(new Pageable() {
            @Override
            public int getPageNumber() {
                return page;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public int getOffset() {
                return page * pageSize;
            }

            @Override
            public Sort getSort() {
                return new Sort("id");
            }
        });
        for (Company company : companiesPage.getContent()) {
            Hibernate.initialize(company.getUsers());
        }
        return companiesPage;
    }
}
