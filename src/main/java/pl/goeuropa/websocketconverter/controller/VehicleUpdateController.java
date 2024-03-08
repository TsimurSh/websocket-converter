package pl.goeuropa.websocketconverter.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.goeuropa.websocketconverter.models.Vehicle;
import pl.goeuropa.websocketconverter.service.VehicleUpdateService;


@Slf4j
@RestController
@RequestMapping("/api")
public class VehicleUpdateController {

    private final VehicleUpdateService service;

    public VehicleUpdateController(VehicleUpdateService service) {
        this.service = service;
    }

    @PostMapping(value = "/create/test")
    @Operation(summary = "Create an object")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Vehicle vehicleUpdate) {
        log.info("Alert has been post : {}", vehicleUpdate);
        service.create(vehicleUpdate);
    }
}
