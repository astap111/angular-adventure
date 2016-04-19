package org.itechart.service.mongo;

import org.itechart.entity.mongo.ProfitAndLoss;
import org.itechart.repository.mongo.ProfitAndLossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportsServiceImpl implements ReportsService {

    @Autowired
    private ProfitAndLossRepository profitAndLossRepository;

    @Override
    public List<ProfitAndLoss> profitAndLoss(Date start, Date end) {
        return profitAndLossRepository.findByDateBetweenOrderByDate(start, end);
    }
}
