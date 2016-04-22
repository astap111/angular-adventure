package org.itechart.service.mongo;

import org.itechart.entity.mongo.company.Company;
import org.itechart.entity.mongo.company.CompanyType;
import org.itechart.other.PageableSortedById;
import org.itechart.repository.mongo.CompanyRepositoryMongo;
import org.itechart.service.ElasticSearchService;
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

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Override
    public void update(Company company) {
        if (company.getId() == null) {
            throw new IllegalArgumentException("Failed to update company with id == null");
        }
        companyRepositoryMongo.save(company);
        elasticSearchService.updateIndexes(company, company.get_class(), company.getId().toString());
    }

    @Override
    public void save(Company company) {
        if (company.getId() != null) {
            throw new IllegalArgumentException("Failed to save company with not null id");
        }
        company.setId(counterService.getNextSequence(Company.class.getSimpleName()));
        companyRepositoryMongo.save(company);
        elasticSearchService.updateIndexes(company, CompanyType.getTypeByClass(company.getClass()).toString(), company.getId().toString());
    }

    @Override
    public Company findOne(Long id) {
        return companyRepositoryMongo.findOne(id);
    }

    @Override
    public Page<Company> findAll(CompanyType companyType, int page, int pageSize) {
        Pageable pageable = new PageableSortedById(page, pageSize);
        return companyRepositoryMongo.findBy_class(companyType.getClassByType(), pageable);
    }
}
