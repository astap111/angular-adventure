package org.itechart.repository.mongo;

import org.itechart.entity.mongo.ProfitAndLoss;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ProfitAndLossRepository extends MongoRepository<ProfitAndLoss, Long> {
    List<ProfitAndLoss> findByDateBetweenOrderByDate(Date start, Date end);
}
