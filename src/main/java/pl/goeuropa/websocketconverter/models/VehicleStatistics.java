package pl.goeuropa.websocketconverter.models;

import lombok.Data;

@Data
public class VehicleStatistics {
   private int rideTime;
   private int idleTime;
   private int parkTime;
   private int maxIdleTime;
   private int distance;
   private int starts;
   private int avgSpeed;
   private int lastStartedAt;
   private int todayStartedAt;
   private int avgFuelConsumed;
   private int maxSpeed;
   private int maxSpeedPercentage;
   private int over90Percentage;
   private int drivingStyle;
   private int realParkTime;
   private int currParkTime;
   private int engineBrakingTime;
   private int engineBrakingPercentage;
   private int ridingOnIdleTime;
   private int ridingOnIdlePercentage;
}
