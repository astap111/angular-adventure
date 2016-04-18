package org.itechart.repository.jpa;

import org.itechart.entity.jpa.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
