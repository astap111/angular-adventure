package org.itechart.controller;

import org.itechart.entity.mongo.ProfitAndLoss;
import org.itechart.service.mongo.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportsService;

    @RequestMapping(value = "profitAndLoss")
    public List<ProfitAndLoss> profitAndLossReport() {
        Date start = new Date(), end = new Date();
        start.setMonth(start.getMonth() - 1);
        return reportsService.profitAndLoss(start, end);
    }
}
