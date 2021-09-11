package mx.challenge.simpleservicestats.validation;

import mx.challenge.simpleservicestats.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

@Component
public class CustomerActiveValidator implements ConstraintValidator<CustomerActive, Long> {

    private final CustomerService customerService;

    @Autowired
    public CustomerActiveValidator(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return customerService.active(value);
    }
}
