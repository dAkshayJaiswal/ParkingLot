package service;

import enums.StatusCode;
import models.ParkingLot;
import models.Status;
import models.Ticket;
import models.Vehicle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParkingService {

    public ParkingLot createParkingLot(Integer size){
        ParkingLot parkingLot = new ParkingLot(size);
        ParkingLot.setParkingLot(parkingLot);
        return parkingLot;
    }

    public Ticket admitVehicle(Vehicle vehicle){

        ParkingLot parkingLot = ParkingLot.getInstance() ;
        if( parkingLot.getCurr_size() == parkingLot.getMax_size() )
        {
            Status status = new Status();
            status.setStatusCode(StatusCode.FAILED);
            status.setMessage("Cannot park since the parking lot is full");
           // throw new Exception(e)

        }
        Integer slotNo = checkFirstEmptySlot() ;
        if( slotNo != -1)
        {
            vehicle.setSlotNo(slotNo);
            parkingLot.getParkingMap().put(slotNo,vehicle);
            // To add logger here
            return vehicle.getTicket();
        }

        return null;
    }

    private Integer checkFirstEmptySlot() {
        ParkingLot parkingLot = ParkingLot.getInstance();

        for(int i = 1 ; i <= parkingLot.getMax_size() ; i++ ){

            if( parkingLot.getParkingMap().containsKey(i))
                continue;
            else
                return i;

        }
        return -1 ;
    }

    public Integer leave(Integer slotNo)
    {
        ParkingLot parkingLot = ParkingLot.getInstance();
        if( parkingLot != null )
        {
            if( parkingLot.getParkingMap().containsKey(slotNo))
                parkingLot.getParkingMap().remove(slotNo);
            return slotNo;

        }
        return null;
    }

    public List<List<String>> getStatus(){
        ParkingLot parkingLot = ParkingLot.getInstance();
        Iterator iterator = parkingLot.getParkingMap().entrySet().iterator();

        List<List<String>> res = new ArrayList<List<String>>();
        List<String> firstRow = new ArrayList<String>();
        firstRow.add("Slot No");
        firstRow.add("Registration No");
        firstRow.add("Colour");
        res.add(firstRow);

        while (iterator.hasNext())
        {
            Map.Entry entry = (Map.Entry) iterator.next();
            List<String> row = new ArrayList<String>();
            Vehicle v = (Vehicle) entry.getValue();
            row.add(v.getSlotNo().toString());
            row.add(v.getRegistrationNo().toString());
            row.add(v.getColor().toString());
            res.add(row);
        }
        return res;
    }


}
