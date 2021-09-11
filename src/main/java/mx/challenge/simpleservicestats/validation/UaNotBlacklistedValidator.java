package mx.challenge.simpleservicestats.validation;

import mx.challenge.simpleservicestats.service.UaBlacklistService;
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
public class UaNotBlacklistedValidator implements ConstraintValidator<UaNotBlacklisted, String> {

    private final UaBlacklistService uaBlacklistService;

    @Autowired
    public UaNotBlacklistedValidator(UaBlacklistService uaBlacklistService) {
        this.uaBlacklistService = uaBlacklistService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !uaBlacklistService.isBlackListed(value);
    }
}
