package org.itechart.service.mongo;

import org.itechart.entity.mongo.ProfitAndLoss;

import java.util.Date;
import java.util.List;

public interface ReportsService {
    List<ProfitAndLoss> profitAndLoss(Date start, Date end);
}
