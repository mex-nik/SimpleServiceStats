package mx.challenge.simpleservicestats.data.db.repository;

import mx.challenge.simpleservicestats.data.db.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 09.09.2021
 * @project SimpleServiceStats
 */

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}