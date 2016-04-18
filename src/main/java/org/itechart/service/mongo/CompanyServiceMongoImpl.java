package org.itechart.service.mongo;

import org.itechart.entity.jpa.company.CompanyType;
import org.itechart.entity.mongo.Company;
import org.itechart.other.PageableSortedById;
import org.itechart.repository.mongo.CompanyRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceMongoImpl implements CompanyServiceMongo {

    @Autowired
    private CompanyRepositoryMongo companyRepositoryMongo;

    @Autowired
    private CounterService counterService;

    @Override
    public void update(Company company) {
        if (company.getId() == null) {
            throw new IllegalArgumentException("Failed to update company with id == null");
        }
        companyRepositoryMongo.save(company);
    }

    @Override
    public void save(Company company) {
        if (company.getId() != null) {
            throw new IllegalArgumentException("Failed to save company with not null id");
        }
        company.setId(counterService.getNextSequence(Company.class.getSimpleName()));
        companyRepositoryMongo.save(company);
    }

    @Override
    public Company findOne(String id) {
        return companyRepositoryMongo.findOne(id);
    }

    @Override
    public Page<Company> findAll(CompanyType companyType, int page, int pageSize) {
        Pageable pageable = new PageableSortedById(page, pageSize);
        return companyRepositoryMongo.findAll(pageable);
    }
}
