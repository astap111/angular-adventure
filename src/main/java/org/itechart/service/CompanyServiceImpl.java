package org.itechart.service;

import org.hibernate.Hibernate;
import org.itechart.entity.company.CarrierCompany;
import org.itechart.entity.company.Company;
import org.itechart.entity.company.CompanyType;
import org.itechart.repository.*;
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
    private WarehouseCompanyRepository warehouseCompanyRepository;

    @Autowired
    private CarrierCompanyRepository carrierCompanyRepository;

    @Autowired
    private SenderCompanyRepository senderCompanyRepository;

    @Autowired
    private ReceiverCompanyRepository receiverCompanyRepository;

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
    public Page<? extends Company> findAll(CompanyType companyType, int page, int pageSize) {
        Pageable pageable = new Pageable() {
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
        };

        Page<? extends Company> companiesPage;

        switch (companyType) {
            case CARRIER_COMPANY:
                companiesPage = carrierCompanyRepository.findAll(pageable);
                for (Company company : companiesPage.getContent()) {
                    Hibernate.initialize(((CarrierCompany) company).getTransports());
                }
                break;
            case RECEIVER_COMPANY:
                companiesPage = receiverCompanyRepository.findAll(pageable);
                break;
            case SENDER_COMPANY:
                companiesPage = senderCompanyRepository.findAll(pageable);
                break;
            case WAREHOUSE_COMPANY:
                companiesPage = warehouseCompanyRepository.findAll(pageable);
                break;
            default:
                companiesPage = companyRepository.findAll(pageable);
        }

        for (Company company : companiesPage.getContent()) {
            Hibernate.initialize(company.getUsers());
        }
        return companiesPage;
    }
}
