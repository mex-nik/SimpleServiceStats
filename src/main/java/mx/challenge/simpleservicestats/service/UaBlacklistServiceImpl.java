package mx.challenge.simpleservicestats.service;

import mx.challenge.simpleservicestats.data.db.repository.UaBlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

@Service
public class UaBlacklistServiceImpl implements UaBlacklistService {

    private final UaBlacklistRepository uaBlacklistRepository;

    @Autowired
    public UaBlacklistServiceImpl(UaBlacklistRepository uaBlacklistRepository) {
        this.uaBlacklistRepository = uaBlacklistRepository;
    }

    @Override
    public boolean isBlackListed(String userAgent) {
        return uaBlacklistRepository.existsById(userAgent);
    }
}
