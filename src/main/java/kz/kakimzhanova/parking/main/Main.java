package kz.kakimzhanova.parking.main;

import kz.kakimzhanova.parking.entity.Car;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();
        Car fourthCar = new Car();
        Car fifthCar = new Car();
        Car sixthCar = new Car();

        firstCar.start();
        secondCar.start();
        thirdCar.start();
        fourthCar.start();
        fifthCar.start();
        sixthCar.start();

        try {
            firstCar.join();
            secondCar.join();
            thirdCar.join();
            fourthCar.join();
            fifthCar.join();
            sixthCar.join();

        }catch (InterruptedException e){
            logger.log(Level.WARN, e);
            Thread.currentThread().interrupt();
        }
    }
}
