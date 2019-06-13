package kz.kakimzhanova.parking.entity;

import kz.kakimzhanova.parking.action.ParkingAction;
import kz.kakimzhanova.parking.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Car extends Thread {
    int carId;
    private static Logger logger = LogManager.getLogger();
    public Car(){
        carId = IdGenerator.generateId();
    }
    @Override
    public void run() {
        ParkingAction parkingAction = new ParkingAction();
        int i = parkingAction.park(carId);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, e);
        }
        if (i != -1) {
            parkingAction.leave(i, carId);
        }
        else {
            i = parkingAction.park(carId);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                logger.log(Level.WARN, e);
            }
            if (i != -1) {
                parkingAction.leave(i, carId);
            } else{
                logger.log(Level.INFO, "Car "+ carId + "could not find parking space");
            }
        }
    }
}
