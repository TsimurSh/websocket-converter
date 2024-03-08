package pl.goeuropa.websocketconverter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import pl.goeuropa.websocketconverter.cache.CacheManager;
import pl.goeuropa.websocketconverter.models.Vehicle;

@Slf4j
@EnableScheduling
@Service
public class VehicleUpdateServiceImpl implements VehicleUpdateService {


    private final CacheManager cacheManager;

    public VehicleUpdateServiceImpl(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Override
    public void create(Vehicle newUpdate) {
        cacheManager.add(newUpdate);
        log.info("-- Added new one {}", newUpdate);
    }
}
