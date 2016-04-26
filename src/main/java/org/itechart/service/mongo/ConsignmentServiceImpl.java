package org.itechart.service.mongo;

import org.itechart.entity.jpa.LifecycleStatus;
import org.itechart.entity.mongo.Consignment;
import org.itechart.other.PageableSortedById;
import org.itechart.repository.mongo.ConsignmentRepository;
import org.itechart.service.StompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ConsignmentServiceImpl implements ConsignmentService {
    @Autowired
    private ConsignmentRepository consignmentRepository;

    @Autowired
    private CounterService counterService;

    @Autowired
    private StompService stompService;

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public void update(Consignment consignment) {
        if (consignment.getId() == null) {
            throw new IllegalArgumentException("Failed to update consignment with id == null");
        }
        productService.saveProducts(consignment.getProducts());
        consignmentRepository.save(consignment);
    }

    @Override
    public void save(Consignment consignment) {
        if (consignment.getId() != null) {
            throw new IllegalArgumentException("Failed to save consignment with not null id");
        }
        productService.saveProducts(consignment.getProducts());
        consignment.setId(counterService.getNextSequence(Consignment.class.getSimpleName()));
        consignmentRepository.save(consignment);
        if (consignment.getStatus() == LifecycleStatus.REGISTERED) {
            stompService.sendNotification("/topic/dispatcher", consignment);
        }
    }

    @Override
    public Consignment findOne(Long id) {
        return consignmentRepository.findOne(id);
    }

    @Override
    public Page<Consignment> findAll(int page, int pageSize) {
        return consignmentRepository.findAll(new PageableSortedById(page, pageSize));
    }

    @Override
    public Page<Consignment> findAll(int page, int pageSize, LifecycleStatus status) {
        return consignmentRepository.findAllByStatus(status, new PageableSortedById(page, pageSize));
    }
}
