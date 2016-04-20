package org.itechart.controller;


import org.itechart.entity.mongo.company.Company;
import org.itechart.entity.mongo.company.CompanyType;
import org.itechart.service.mongo.CompanyServiceMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyServiceMongo companyServiceMongo;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Company> getCompanies(@RequestParam CompanyType companyType, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        return companyServiceMongo.findAll(companyType, page, pageSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company getCompany(@PathVariable Long id) {
        return companyServiceMongo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createWarehouse(@RequestBody Company company) {
        companyServiceMongo.save(company);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateCompany(@RequestBody Company company) {
        companyServiceMongo.update(company);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
