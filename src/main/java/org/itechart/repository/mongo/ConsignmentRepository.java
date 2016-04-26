package org.itechart.repository.mongo;

import org.itechart.entity.jpa.LifecycleStatus;
import org.itechart.entity.mongo.Consignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsignmentRepository extends MongoRepository<Consignment, Long> {
    Page<Consignment> findAllByStatus(LifecycleStatus status, Pageable pageable);
}
