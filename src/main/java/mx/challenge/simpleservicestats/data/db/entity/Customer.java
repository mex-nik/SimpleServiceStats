package mx.challenge.simpleservicestats.data.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;
    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<HourlyStats> stats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && name.equals(customer.name) && active.equals(customer.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active);
    }
}
