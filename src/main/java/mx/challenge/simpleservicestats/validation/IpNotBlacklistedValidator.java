package mx.challenge.simpleservicestats.validation;

import mx.challenge.simpleservicestats.service.IpBlacklistService;
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
public class IpNotBlacklistedValidator implements ConstraintValidator<IpNotBlacklisted, String> {

    private final IpBlacklistService ipBlacklistService;

    @Autowired
    public IpNotBlacklistedValidator(IpBlacklistService ipBlacklistService) {
        this.ipBlacklistService = ipBlacklistService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !ipBlacklistService.isBlackListed(value);
    }
}
