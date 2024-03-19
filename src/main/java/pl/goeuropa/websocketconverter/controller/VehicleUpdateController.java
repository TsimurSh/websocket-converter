package pl.goeuropa.websocketconverter.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.goeuropa.websocketconverter.models.Vehicle;
import pl.goeuropa.websocketconverter.service.VehicleUpdateService;

import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/kombus")
public class VehicleUpdateController {

    private final VehicleUpdateService service;

    public VehicleUpdateController(VehicleUpdateService service) {
        this.service = service;
    }

    @PostMapping("/update-position")
    public void create(@RequestBody Vehicle vehicleUpdate) {
        log.info("Position has been got : {}", vehicleUpdate);
        service.putUpdateVehicle(vehicleUpdate);
    }

    @GetMapping("/positions.text")
    public String get() {
        var asText = service.getUpdatedVehicles();
        log.info("Got positions as text: {}", asText.lines()
                .collect(Collectors.joining()));
        return asText;
    }
}
