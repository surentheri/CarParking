package carparkingPOJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import carParkingUtils.CarParkingUtils;
import carparking.CarParkingAction;

public class ScrCarParkingPOJO implements Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private String Floor ;
private Hashtable<String,ScrVechiclePOJO> Vechicles = new Hashtable<>();
private ArrayList<String> VechiclesNum = new ArrayList<>();
private int available = 0 ;
private int capacity;

public String getFloor() {
	return Floor;
}
public void setFloor(String floor) {
	Floor = floor;
}
public ArrayList<String> getVechicles() {
	return VechiclesNum;
}

public void setVechicles(String num) {
	VechiclesNum.add(num);
}
public int getAvailable() {
	return available;
}
public void setAvailable(int available) {
	this.available = available;
}
public void reduceAvailable() {
	this.available--;
}
public void increaseAvailble() {
	this.available++;
}
public int getCapacity() {
	return capacity;
}
public void setCapacity(int capacity) {
	this.capacity = capacity;
} 
}
