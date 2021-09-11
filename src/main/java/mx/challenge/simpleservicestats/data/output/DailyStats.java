package mx.challenge.simpleservicestats.data.output;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.challenge.simpleservicestats.data.db.entity.HourlyStats;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 11.09.2021
 * @project SimpleServiceStats
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class DailyStats {
    private List<HourlyStats> hourlyStats;
    private BigInteger totalCount;
}
