package pl.goeuropa.websocketconverter.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.goeuropa.websocketconverter.models.Vehicle;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class CacheManager implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private static List<Vehicle> test = new LinkedList<>();


    public void add(Vehicle newUpdate) {
        log.info("Add to cache a vehicleUpdate object : {}", newUpdate);
        test.add(newUpdate);
    }
}
