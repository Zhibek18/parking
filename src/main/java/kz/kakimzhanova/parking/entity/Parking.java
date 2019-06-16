package kz.kakimzhanova.parking.entity;

import kz.kakimzhanova.parking.exception.WrongParkingIndexException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {
    private static final int PARKING_SPACE_COUNT = 4;
    private static Parking instance;
    private static Lock lock = new ReentrantLock(true);
    private static AtomicBoolean created = new AtomicBoolean(false);

    private List <ParkingSpace> parkingSpaces;

    private Parking() {
        parkingSpaces = new ArrayList<ParkingSpace>();
        for (int i = 0; i < PARKING_SPACE_COUNT; i++) {
            parkingSpaces.add(new ParkingSpace());
        }
    }

    public static Parking getInstance() {
        if (!created.get()){
            lock.lock();
            try{
                if (instance == null){
                    instance = new Parking();
                    created.set(true);
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static int getParkingSpaceCount() {
        return PARKING_SPACE_COUNT;
    }

    public boolean isFree(int i) throws WrongParkingIndexException {
        if ((i < PARKING_SPACE_COUNT) && (i >= 0)) {
            return parkingSpaces.get(i).isFree();
        }
        else throw new WrongParkingIndexException("Wrong index i = " + i);
    }

    public void takeParkingSpace(int i, int carId){ // change name
        parkingSpaces.get(i).setTaken(carId);
    }

    public void freeParkingSpace(int i){ //change name
        parkingSpaces.get(i).setFree();
    }
}
