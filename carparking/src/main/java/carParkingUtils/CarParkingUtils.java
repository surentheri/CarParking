package carParkingUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import carparkingPOJO.ScrVechiclePOJO;

public class CarParkingUtils {

	private static RedissonClient redisObj = null;
	public static String serverpath = "C:\\Users\\Suren\\eclipse-workspace\\carparking";
	private String RedisMap = "REDISMAP";
	private String rediskey = "SCRBEEN";
	private String redisVechKey = "VECHICLEHASHMAP";
	private static HashMap<Integer, Integer> hmCost = new HashMap<>();
	private static int slot;
	private static int slotsize;

	public void setRedisObj(RedissonClient redisObj) {
		CarParkingUtils.redisObj = redisObj;
	}

	public void setValueInRedis(Object object) {
		RMap<String, byte[]> map = redisObj.getMap(RedisMap);
		map.put(rediskey, serialize(object));
	}

	public Object getValueInRedis() {
		return deSerialize((byte[]) redisObj.getMap(RedisMap).get(rediskey));
	}

	public void setCost(int k, int v) {
		hmCost.put(k, v);
	}

	public static int getCost(int k) {
		return hmCost.get(k);

	}

	public static byte[] serialize(Object obj) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			;
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bytes = baos.toByteArray();
			baos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public static Object deSerialize(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public void setValueInRedis(String vechicleNo, ScrVechiclePOJO scrVechiclePOJOObj) {
		RMap<String, byte[]> map = redisObj.getMap(redisVechKey);
		map.put(vechicleNo, serialize(scrVechiclePOJOObj));
	}

	public Object getValueInRedis(String vechicleNo) {
		return redisObj.getMap(redisVechKey).containsKey(vechicleNo)
				? deSerialize((byte[]) redisObj.getMap(redisVechKey).get(vechicleNo))
				: null;
	}

	public void setSlot(int slot) {
		CarParkingUtils.slot = slot;
	}

	public void setSlotSize(int slotSize) {
		CarParkingUtils.slotsize = slotSize;
	}

	public String getSolt() {
		return CarParkingUtils.slot + ":" + CarParkingUtils.slotsize;
	}

	public void setVechicles(ScrVechiclePOJO scrVechiclePOJOObj) {
		new CarParkingUtils().setValueInRedis(scrVechiclePOJOObj.getVechicleNo(), scrVechiclePOJOObj);
	}

	public ScrVechiclePOJO getVechicles(String vechicleNo) {
		return (ScrVechiclePOJO) new CarParkingUtils().getValueInRedis(vechicleNo);
	}

}
