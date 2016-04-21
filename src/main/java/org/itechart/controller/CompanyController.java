package org.itechart.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itechart.entity.company.Company;
import org.itechart.entity.company.CompanyType;
import org.itechart.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {
    private static final Logger LOGGER = LogManager.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<? extends Company> getCompanies(@RequestParam CompanyType companyType, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {

        return companyService.findAll(companyType, page == null ? 0 : page, pageSize== null ? 0 :pageSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company getCompany(@PathVariable Long id) {
        return companyService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createCompany(@RequestBody Company company) {
        companyService.save(company);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateUser(@RequestBody Company company) {
        companyService.update(company);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
