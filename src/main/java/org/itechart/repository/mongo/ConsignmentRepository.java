package org.itechart.repository.mongo;

import org.itechart.entity.mongo.Consignment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsignmentRepository extends MongoRepository<Consignment, Long> {
}
