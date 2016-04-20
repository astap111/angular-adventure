package org.itechart.service.mongo;

import org.itechart.entity.mongo.Consignment;
import org.springframework.data.domain.Page;

public interface ConsignmentService {
    void update(Consignment company);

    void save(Consignment company);

    Consignment findOne(Long id);

    Page<Consignment> findAll(int page, int pageSize);
}
