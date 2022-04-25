package carparking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;

import carParkingUtils.CarParking;
import carParkingUtils.CarParkingUtils;
import carparkingPOJO.ScrCarParkingPOJO;
import carparkingPOJO.ScrVechiclePOJO;
import jakarta.servlet.ServletException;

public class CarParkingAction implements CarParking {

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject CheckIn(String vechicleNo, String type, String lot, String checkinTime) {
		JSONObject json = new JSONObject();
		json.put("result", false);
		CarParkingUtils CarParkingUtilsObj = new CarParkingUtils();
		ScrCarParkingPOJO[] obj = (ScrCarParkingPOJO[]) CarParkingUtilsObj.getValueInRedis();
		for (ScrCarParkingPOJO ScrCarParkingPOJOObj : obj) {
			if (lot.equals(ScrCarParkingPOJOObj.getFloor())) {
				if (ScrCarParkingPOJOObj.getAvailable() > 0) {
					ScrVechiclePOJO ScrVechilePOJOObj = new ScrVechiclePOJO();
					ScrVechilePOJOObj.setCheckIn(checkinTime);
					ScrVechilePOJOObj.setIsAvailble(true);
					ScrVechilePOJOObj.setFloor(lot);
					String slot = Integer
							.toString(ScrCarParkingPOJOObj.getCapacity() - ScrCarParkingPOJOObj.getAvailable());
					ScrVechilePOJOObj.setSlot(slot);
					ScrVechilePOJOObj.setVechicleNo(vechicleNo);
					ScrCarParkingPOJOObj.setVechicles(vechicleNo);
					ScrCarParkingPOJOObj.reduceAvailable();
					CarParkingUtilsObj.setValueInRedis(obj);
					CarParkingUtilsObj.setVechicles(ScrVechilePOJOObj);
					json.put("slot", slot);
					json.put("result", true);
					return json;
				}
			}
		}
		return json;
	}

	@Override
	public JSONObject CheckOut(String vechicleNo, String time) {
		JSONObject json = new JSONObject();
		json.put("result", false);
		CarParkingUtils CarParkingUtilsObj = new CarParkingUtils();
		ScrCarParkingPOJO[] obj = (ScrCarParkingPOJO[]) CarParkingUtilsObj.getValueInRedis();

		ScrVechiclePOJO ScrVechiclePOJOObj = CarParkingUtilsObj.getVechicles(vechicleNo);
		if (ScrVechiclePOJOObj != null) {
			for (ScrCarParkingPOJO ScrCarParkingPOJOObj : obj) {
				if (ScrCarParkingPOJOObj.getFloor().equals(ScrVechiclePOJOObj.getFloor())) {
					ScrCarParkingPOJOObj.increaseAvailble();
					break;
				}
			}
			ScrVechiclePOJOObj.setCheckOut(time);
			ScrVechiclePOJOObj.setIsAvailble(false);
			StringBuilder sb = new StringBuilder();
			if(ScrVechiclePOJOObj.getHistory()!=null) {
			sb.append(ScrVechiclePOJOObj.getHistory() + "-");
			}
			sb.append(ScrVechiclePOJOObj.getFloor() + ";" + ScrVechiclePOJOObj.getCheckIn() + ";"
					+ ScrVechiclePOJOObj.getCheckOut());
			ScrVechiclePOJOObj.setHistory(sb.toString());
			int min = timeDifferenceInMin(ScrVechiclePOJOObj.getCheckOut(), ScrVechiclePOJOObj.getCheckIn());
			float cost = min
					* (float) ((float) new CarParkingUtils().getCost(ScrVechiclePOJOObj.getType()) / (float) 60);
			json.put("result", true);
			json.put("cost", cost);
			json.put("time", "hour :" + (min / 60) + " Min : " + (min % 60) + " ");
			CarParkingUtilsObj.setValueInRedis(obj);
			CarParkingUtilsObj.setVechicles(ScrVechiclePOJOObj);

			return json;
		}
		return json;
	}

	private int timeDifferenceInMin(String checkIn, String checkOut) {
		try {
			Date date1 = new SimpleDateFormat("HH:mm").parse(checkIn);
			Date date2 = new SimpleDateFormat("HH:mm").parse(checkOut);
			long diff = date1.getTime() - date2.getTime();
			return (int) TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);

		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	@Override
	public JSONObject showLot() {
		CarParkingUtils CarParkingUtilsObj = new CarParkingUtils();
		ScrCarParkingPOJO[] obj = (ScrCarParkingPOJO[]) CarParkingUtilsObj.getValueInRedis();
		JSONObject json = new JSONObject();
		json.put("obj", obj);
		return json;
	}

	@Override
	public JSONObject FindMyVechicle(String vechicleNo) {
		JSONObject json = new JSONObject();
		CarParkingUtils CarParkingUtilsObj = new CarParkingUtils();
		ScrVechiclePOJO ScrVechiclePOJOObj = CarParkingUtilsObj.getVechicles(vechicleNo);
		if (ScrVechiclePOJOObj == null) {
			json.put("result", "Car not parked in the parking");
			json.put("history", "no history found");
		} else {
			if (!ScrVechiclePOJOObj.isIsAvailble()) {
				json.put("result", "Car not parked in the parking");

			} else {
				int slo = Integer.parseInt(ScrVechiclePOJOObj.getSlot()) + 1;
				json.put("result", "Car parked in the " + ScrVechiclePOJOObj.getFloor() + slo + " parking");
				String his = ScrVechiclePOJOObj.getHistory() == null ? "no history found "
						: ScrVechiclePOJOObj.getHistory();
				json.put("history", his);
			}
		}
		return json;
	}

}
