package mx.challenge.simpleservicestats.service;

import mx.challenge.simpleservicestats.data.db.repository.IpBlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

@Service
public class IpBlacklistServiceImpl implements IpBlacklistService {

    private final IpBlacklistRepository ipBlacklistRepository;

    @Autowired
    public IpBlacklistServiceImpl(IpBlacklistRepository ipBlacklistRepository) {
        this.ipBlacklistRepository = ipBlacklistRepository;
    }

    @Override
    public boolean isBlackListed(String ipAddress) {
        return ipBlacklistRepository.existsById(ipAddress);
    }
}
