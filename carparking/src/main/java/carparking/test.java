package carparking;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import carparkingPOJO.ScrCarParkingPOJO;

public class test {

	public static void main(String[] args) {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://192.168.181.55:6379");

		RedissonClient redisson = Redisson.create(config);
		ScrCarParkingPOJO obj = new ScrCarParkingPOJO();
		obj.setAvailable(10);
		// perform operations

		String[] i = new String[5];
		i[0]="suren";
		RMap<String, Object> map = redisson.getMap("map");
		map.put("int", serialize(obj));

		RMap<String, Object> map1 = redisson.getMap("map");
		System.out.println(((ScrCarParkingPOJO)( deSerialize((byte[])(map1.get("int"))))).getAvailable());
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

}
