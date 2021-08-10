package models;

import enums.Color;
import lombok.Getter;
import lombok.Setter;

import javax.print.DocFlavor;

@Getter
@Setter
public class Vehicle {

    String registrationNo ;
    Color color ;
    Integer slotNo ;
    Ticket ticket ;

    public void setSlotNo(Integer slotNo) {
        this.slotNo = slotNo;
        this.ticket = new Ticket();
    }
}
