package kz.kakimzhanova.parking.entity;

import kz.kakimzhanova.parking.action.CarAction;
import kz.kakimzhanova.parking.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Car extends Thread {
    private static final long CAR_WAITING_TIME = 5;
    private static Logger logger = LogManager.getLogger();
    private static Semaphore semaphore = new Semaphore(Parking.getParkingSpaceCount());
    private int carId;
    public Car(){
        carId = IdGenerator.generateId();
    }

    @Override
    public void run() {
        try {
            if (semaphore.tryAcquire(CAR_WAITING_TIME, TimeUnit.SECONDS)){
                CarAction carAction = new CarAction();
                int parkingSpaceId = carAction.park(carId);
                Random random = new SecureRandom();
                int parkingTime = random.nextInt(10);
                logger.log(Level.INFO, "Car "+ carId + " will remain for " + parkingTime + " s" );
                TimeUnit.SECONDS.sleep(parkingTime);

                if (parkingSpaceId != -1) {
                    carAction.leave(parkingSpaceId, carId);
                }
                else {
                    logger.log(Level.WARN, "Car "+ carId + " no parking space found");
                }
                semaphore.release();
            }
            else{
                logger.log(Level.INFO, "Car "+ carId + " waiting timer expired. Leaves parking");
            }
        } catch (InterruptedException e) {
            logger.log(Level.WARN, e);
            Thread.currentThread().interrupt();
        }
    }
}
