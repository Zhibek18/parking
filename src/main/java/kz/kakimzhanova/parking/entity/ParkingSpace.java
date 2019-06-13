package kz.kakimzhanova.parking.entity;

public class ParkingSpace {
    private boolean free;
    private int carId;

    public ParkingSpace(){
        free = true;
        carId = 0;
    }
    public boolean isFree(){
        return free;
    }

    public int getCarId() {
        return carId;
    }

    public void parkCar(int carId) {
        carId = carId;
        free = false;
    }

    public void setFree(){
        free = true;
        carId = 0;
    }
}
