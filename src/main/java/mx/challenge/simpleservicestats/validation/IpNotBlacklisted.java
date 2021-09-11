package mx.challenge.simpleservicestats.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = IpNotBlacklistedValidator.class)
public @interface IpNotBlacklisted {
    String message() default "{IpAddress.blacklisted}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
