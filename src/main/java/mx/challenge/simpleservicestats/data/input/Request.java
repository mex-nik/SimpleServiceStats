package mx.challenge.simpleservicestats.data.input;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.challenge.simpleservicestats.validation.CustomerActive;
import mx.challenge.simpleservicestats.validation.CustomerExists;
import mx.challenge.simpleservicestats.validation.IpNotBlacklisted;
import mx.challenge.simpleservicestats.validation.UaNotBlacklisted;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 09.09.2021
 * @project SimpleServiceStats
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Request {
    @NotNull(message = "customerID is mandatory")
    @CustomerExists(message = "Customer not in DB")
    @CustomerActive(message = "Customer disabled")
    private Long customerID;
    @NotNull
    private Long tagID;
    @NotNull
    @NotBlank(message = "userID is mandatory")
    @Pattern(regexp = "^[a-z]{8}(-[a-z]{4}){2}-[0-9]{4}-[0-9]{12}$",
            message = "userID not valid")
    @UaNotBlacklisted(message = "User Agent blacklisted")
    private String userID;
    @NotNull
    @NotBlank(message = "remoteIP is mandatory")
    @Pattern(regexp = "^([0-1]?\\d?\\d|2[0-4]\\d|25[0-5])(\\.([0-1]?\\d?\\d|2[0-4]\\d|25[0-5])){3}$",
            message = "remoteIP not valid")
    @IpNotBlacklisted(message = "IP address blacklisted")
    private String remoteIP;
    @NotNull
    @PastOrPresent(message = "Timestamp not valid")
    private Timestamp timestamp;
}
