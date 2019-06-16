package kz.kakimzhanova.parking.util;

public class IdGenerator {
    private static int id = 1;
    private IdGenerator(){}
    public static int generateId(){
        return id++;
    }
}
