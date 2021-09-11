package mx.challenge.simpleservicestats.data.db.repository;

import mx.challenge.simpleservicestats.data.db.entity.HourlyStats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 09.09.2021
 * @project SimpleServiceStats
 */

@Repository
public interface HourlyStatsRepository extends CrudRepository<HourlyStats, Long> {
    Optional<HourlyStats> findByCustomer_IdAndTime(Long id, LocalDateTime time);

    List<HourlyStats> findByCustomer_IdAndTimeIsBetween(Long id, LocalDateTime timeStart, LocalDateTime timeEnd);
}
