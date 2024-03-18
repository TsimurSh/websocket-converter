package pl.goeuropa.websocketconverter.service;

import com.google.transit.realtime.GtfsRealtime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.goeuropa.websocketconverter.cache.CacheManager;
import pl.goeuropa.websocketconverter.gtfsrt.GtfsRealTimeVehicleFeed;
import pl.goeuropa.websocketconverter.models.Vehicle;

import java.io.FileOutputStream;

@Slf4j
@EnableScheduling
@Service
public class VehicleUpdateServiceImpl implements VehicleUpdateService {


    private final CacheManager cacheManager;

    public VehicleUpdateServiceImpl(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Value("${api.out-path}")
    private String OUTPUT_PATH;

    @Override
    public void putUpdateVehicle(Vehicle newUpdate) {
        cacheManager.add(newUpdate);
    }

    @Override
    @Scheduled(cron = "*/5 * * * * *")
    public String getUpdatedVehicles() {
        try {
            GtfsRealtime.FeedMessage feed = new GtfsRealTimeVehicleFeed().createMessage(cacheManager);
            log.info("Wrote: {} entities.", feed.getEntityCount());

            //Writing to protobuf file
            var toFile = new FileOutputStream(OUTPUT_PATH);
            feed.writeTo(toFile);
            toFile.close();

            return feed.toString();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ex.getMessage() + ex.getCause();
        }
    }
}
