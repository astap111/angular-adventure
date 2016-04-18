package org.itechart.service;

import org.itechart.entity.jpa.company.Company;
import org.itechart.entity.jpa.company.CompanyType;
import org.itechart.other.PageableSortedById;
import org.itechart.repository.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return companyRepository.findOne(id);
    }

    @Override
    public Page<? extends Company> findAll(CompanyType companyType, int page, int pageSize) {
        Pageable pageable = new PageableSortedById(page, pageSize);

        Page<? extends Company> companiesPage = null;

        if (companyType == null) {
            companiesPage = companyRepository.findAll(pageable);
        } else {
            switch (companyType) {
                case CARRIER_COMPANY:
                    companiesPage = carrierCompanyRepository.findAll(pageable);
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
            }
        }

        return companiesPage;
    }

    @Override
    public List<? extends Company> findAll(CompanyType companyType) {
        List<? extends Company> companiesPage = null;

        switch (companyType) {
            case CARRIER_COMPANY:
                companiesPage = carrierCompanyRepository.findAll();
                break;
            case RECEIVER_COMPANY:
                companiesPage = receiverCompanyRepository.findAll();
                break;
            case SENDER_COMPANY:
                companiesPage = senderCompanyRepository.findAll();
                break;
            case WAREHOUSE_COMPANY:
                companiesPage = warehouseCompanyRepository.findAll();
                break;
        }

        return companiesPage;
    }
}
