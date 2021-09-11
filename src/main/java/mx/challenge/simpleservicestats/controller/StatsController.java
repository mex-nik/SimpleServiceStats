package mx.challenge.simpleservicestats.controller;

import mx.challenge.simpleservicestats.data.output.DailyStats;
import mx.challenge.simpleservicestats.service.HourlyStatsService;
import mx.challenge.simpleservicestats.validation.CustomerExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.PastOrPresent;
import java.util.Date;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

@RestController
@RequestMapping("stats")
public class StatsController {

    private final HourlyStatsService hourlyStatsService;

    @Autowired
    public StatsController(HourlyStatsService hourlyStatsService) {
        this.hourlyStatsService = hourlyStatsService;
    }

    @GetMapping("hourlyForDay")
    public DailyStats getStatsDay(@RequestParam(name = "customerId") @CustomerExists Long customerId,
                                  @RequestParam(name = "day") @PastOrPresent @DateTimeFormat(pattern = "dd-MM-yyyy") Date day) {
        return hourlyStatsService.getStats(customerId, day);
    }
}
