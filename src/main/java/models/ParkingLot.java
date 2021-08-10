package models;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class ParkingLot {

    HashMap<Integer,Vehicle> parkingMap ;
    Integer max_size ;
    Integer curr_size ;

    public static void setParkingLot(ParkingLot parkingLot) {
        ParkingLot.parkingLot = parkingLot;
    }

    private static ParkingLot parkingLot = null;
    public static ParkingLot getInstance()
    {
        return parkingLot;
    }

    public ParkingLot(Integer a) {

        max_size = a ;
        parkingMap = new HashMap<Integer, Vehicle>();
        curr_size = 0 ;
    }



}
