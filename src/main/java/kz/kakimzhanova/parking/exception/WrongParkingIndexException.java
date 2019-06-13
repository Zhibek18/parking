package kz.kakimzhanova.parking.exception;

public class WrongParkingIndexException extends Exception{
    public WrongParkingIndexException() {
    }

    public WrongParkingIndexException(String s) {
        super(s);
    }

    public WrongParkingIndexException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongParkingIndexException(Throwable throwable) {
        super(throwable);
    }
}
