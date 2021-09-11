package mx.challenge.simpleservicestats.service;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

public interface IpBlacklistService {
    boolean isBlackListed(String ipAddress);
}
