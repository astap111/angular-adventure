package org.itechart.service.mongo;

import org.itechart.entity.mongo.Consignment;
import org.itechart.other.PageableSortedById;
import org.itechart.repository.mongo.ConsignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsignmentServiceImpl implements ConsignmentService {

    @Autowired
    private ConsignmentRepository consignmentRepository;

    @Override
    public void update(Consignment consignment) {
        if (consignment.getId() == null) {
            throw new IllegalArgumentException("Failed to update consignment with id == null");
        }
        consignmentRepository.save(consignment);
    }

    @Override
    public void save(Consignment consignment) {
        if (consignment.getId() != null) {
            throw new IllegalArgumentException("Failed to save consignment with not null id");
        }
        consignmentRepository.save(consignment);
    }

    @Override
    public Consignment findOne(Long id) {
        return consignmentRepository.findOne(id);
    }

    @Override
    public Page<Consignment> findAll(int page, int pageSize) {
        return consignmentRepository.findAll(new PageableSortedById(page, pageSize));
    }
}
