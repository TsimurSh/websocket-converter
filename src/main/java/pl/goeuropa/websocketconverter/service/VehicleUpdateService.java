package pl.goeuropa.websocketconverter.service;


import pl.goeuropa.websocketconverter.models.Vehicle;

public interface VehicleUpdateService {

    void putUpdateVehicle(Vehicle newUpdate);

    String getUpdatedVehicles();
}
