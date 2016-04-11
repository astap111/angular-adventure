package org.itechart.repository;

import org.itechart.entity.Consignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsignmentRepository extends JpaRepository<Consignment, Long> {
}
