package kz.kakimzhanova.parking.entity;


import kz.kakimzhanova.parking.exception.WrongParkingIndexException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {
    private static final int N = 2;
    private static Parking instance;
    private static Lock lock = new ReentrantLock(true);
    private static AtomicBoolean created = new AtomicBoolean(false);

    private List <ParkingSpace> parkingSpaces = new ArrayList<ParkingSpace>();

    private Parking(){
        for (int i = 0; i < N; i++){
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
    public int getN(){
        return N;
    }
    public boolean isFree(int i) throws WrongParkingIndexException{
        if ((i < N) && (i >= 0)) {
            return parkingSpaces.get(i).isFree();
        }
        else throw new WrongParkingIndexException("Wrong index i = " + i);
    }

    public void takeParkingSpace(int i, int carId){
        parkingSpaces.get(i).parkCar(carId);
    }

    public void freeParkingSpace(int i){
        parkingSpaces.get(i).setFree();
    }
}
