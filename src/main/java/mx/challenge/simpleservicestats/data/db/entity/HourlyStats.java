package mx.challenge.simpleservicestats.data.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 09.09.2021
 * @project SimpleServiceStats
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@JsonSerialize
public class HourlyStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @NonNull
    private LocalDateTime time;
    @NonNull
    private BigInteger requestCount;
    @NonNull
    private BigInteger invalidCount;
}
