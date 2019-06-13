package kz.kakimzhanova.parking.util;

public class IdGenerator {
    private static int id = 1;

    public static int generateId(){
        return id++;
    }
}
