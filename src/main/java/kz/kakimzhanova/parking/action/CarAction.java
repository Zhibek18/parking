package kz.kakimzhanova.parking.action;

import kz.kakimzhanova.parking.entity.Parking;
import kz.kakimzhanova.parking.exception.WrongParkingIndexException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarAction {
    private static Logger logger = LogManager.getLogger();
    private static Lock lock = new ReentrantLock(true);

    public int park(int carId){
        Parking parking = Parking.getInstance();
        lock.lock();
        try{
            for (int i = 0; i < Parking.getParkingSpaceCount(); i++) {
                if (parking.isFree(i)) {
                    parking.takeParkingSpace(i, carId);
                    logger.log(Level.INFO, "Car " + carId + " has taken space number " + i);
                    return i;
                }
            }
        } catch (WrongParkingIndexException e) {
            logger.log(Level.WARN, e);
        } finally {
            lock.unlock();
        }
        logger.log(Level.INFO, "Car " + carId + "No free parking spaces available");
        return -1;
    }

    public void leave(int i, int carId){
        Parking.getInstance().freeParkingSpace(i);
        logger.log(Level.INFO, "Car " + carId + " has left space number " + i);
    }
}
