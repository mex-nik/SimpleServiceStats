package mx.challenge.simpleservicestats.service;

import mx.challenge.simpleservicestats.data.db.entity.HourlyStats;
import mx.challenge.simpleservicestats.data.output.DailyStats;

import java.util.Date;
import java.sql.Timestamp;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

public interface HourlyStatsService {
    void incrementValid(Long customerId, Timestamp hour);
    void incrementInvalid(Long customerId, Timestamp hour);
    HourlyStats getStats(Long customerId, Timestamp hour);
    DailyStats getStats(Long customerId, Date date);
}
