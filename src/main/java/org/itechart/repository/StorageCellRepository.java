package org.itechart.repository;

import org.itechart.entity.StorageCell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageCellRepository extends JpaRepository<StorageCell, Long> {
}
