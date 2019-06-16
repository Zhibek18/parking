package kz.kakimzhanova.parking.entity;

public class ParkingSpace {
    private boolean free;
    private int carId;

    ParkingSpace(){
        this.free = true;
        this.carId = 0;
    }
    public boolean isFree(){
        return free;
    }

    public int getCarId() {
        return carId;
    }

    public void setTaken(int carId) {
        this.carId = carId;
        this.free = false;
    }

    void setFree(){
        this.free = true;
        this.carId = 0;
    }
}
