package mx.challenge.simpleservicestats.service;

import mx.challenge.simpleservicestats.data.db.entity.Customer;
import mx.challenge.simpleservicestats.data.db.entity.HourlyStats;
import mx.challenge.simpleservicestats.data.db.repository.CustomerRepository;
import mx.challenge.simpleservicestats.data.db.repository.HourlyStatsRepository;
import mx.challenge.simpleservicestats.data.output.DailyStats;
import mx.challenge.simpleservicestats.validation.CustomerExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigInteger;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

@Service
public class HourlyStatsServiceImpl implements HourlyStatsService {

    private final HourlyStatsRepository hourlyStatsRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public HourlyStatsServiceImpl(HourlyStatsRepository hourlyStatsRepository, CustomerRepository customerRepository) {
        this.hourlyStatsRepository = hourlyStatsRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void incrementValid(@NotNull @CustomerExists Long customerId, @PastOrPresent Timestamp hour) {
        HourlyStats hourlyStats = hourlyStatsRepository.findByCustomer_IdAndTime(customerId, roundedToHours(hour))
                .orElse(createNew(customerId, hour));
        hourlyStats.setRequestCount(hourlyStats.getRequestCount().add(BigInteger.ONE));
        hourlyStatsRepository.save(hourlyStats);
    }

    @Override
    public void incrementInvalid(@NotNull @CustomerExists Long customerId, @PastOrPresent Timestamp hour) {
        HourlyStats hourlyStats = hourlyStatsRepository.findByCustomer_IdAndTime(customerId, roundedToHours(hour))
                .orElse(createNew(customerId, hour));
        hourlyStats.setInvalidCount(hourlyStats.getInvalidCount().add(BigInteger.ONE));
        hourlyStatsRepository.save(hourlyStats);
    }

    @Override
    public HourlyStats getStats(@NotNull @CustomerExists Long customerId, @PastOrPresent Timestamp hour) {

        return hourlyStatsRepository.findByCustomer_IdAndTime(customerId, roundedToHours(hour)).orElse(null);
    }

    @Override
    public DailyStats getStats(Long customerId, Date date) {
        DailyStats dailyStats = new DailyStats();
        dailyStats.setHourlyStats(hourlyStatsRepository.findByCustomer_IdAndTimeIsBetween(customerId, date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime(), date.toInstant()
                .plus(23, ChronoUnit.HOURS)
                .plus(59, ChronoUnit.MINUTES)
                .plus(59, ChronoUnit.SECONDS)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()));
        dailyStats.setTotalCount(dailyStats.getHourlyStats().stream().map(hourlyStats ->
            hourlyStats.getRequestCount().add(hourlyStats.getInvalidCount())).reduce(BigInteger.ZERO, (subtotal, element) -> subtotal.add(element)));
        return dailyStats;
    }

    private HourlyStats createNew(@NotNull @CustomerExists Long customerId, @PastOrPresent Timestamp hour){
        Customer customer = customerRepository.findById(customerId).get();
        HourlyStats hourlyStats = new HourlyStats();
        hourlyStats.setCustomer(customer);
        hourlyStats.setRequestCount(BigInteger.ZERO);
        hourlyStats.setInvalidCount(BigInteger.ZERO);
        hourlyStats.setTime(roundedToHours(hour));

        return hourlyStats;
    }

    private LocalDateTime roundedToHours(@NotNull Timestamp timestamp) {
        return Timestamp.from(timestamp.toInstant().truncatedTo(ChronoUnit.HOURS)).toLocalDateTime();
    }
}
