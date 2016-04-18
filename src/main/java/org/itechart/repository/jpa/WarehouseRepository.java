package org.itechart.repository.jpa;

import org.itechart.entity.jpa.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
