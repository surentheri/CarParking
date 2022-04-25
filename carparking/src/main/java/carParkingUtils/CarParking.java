package carParkingUtils;
import org.json.simple.JSONObject;
public interface CarParking {

	JSONObject CheckIn(String vechicleNo,String type, String date,String checkinTime);
	JSONObject CheckOut(String vechicleNo,String time);
	JSONObject showLot();
	JSONObject FindMyVechicle(String vechicleNo);
}
