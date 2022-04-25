package carparkingPOJO;

import java.io.Serializable;
import java.util.ArrayList;

import carParkingUtils.CarParkingUtils;

public class ScrVechiclePOJO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String vechicleNo;
	private String checkIn;
	private String checkOut;
	private String Date;
	private boolean IsAvailble;
	private String floor;
	private String slot;
	private int type = 0;
	
	private String history ;
	public String getVechicleNo() {
		return vechicleNo;
	}
	public void setVechicleNo(String vechicleNo) {
		this.vechicleNo = vechicleNo;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String string) {
		this.history=string;
	}
	public boolean isIsAvailble() {
		return IsAvailble;
	}
	public void setIsAvailble(boolean isAvailble) {
		IsAvailble = isAvailble;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	public int getTypeCost() {
		return CarParkingUtils.getCost(type);
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
