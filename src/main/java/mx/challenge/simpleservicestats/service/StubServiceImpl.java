package mx.challenge.simpleservicestats.service;

import lombok.extern.slf4j.Slf4j;
import mx.challenge.simpleservicestats.data.input.Request;
import org.springframework.stereotype.Service;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

@Slf4j
@Service
public class StubServiceImpl implements StubService{
    @Override
    public void stubMethod(Request request) {
        log.info("Process req={}", request);
    }
}
