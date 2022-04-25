package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import carParkingUtils.CarParkingUtils;
import carparkingPOJO.ScrCarParkingPOJO;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		Properties p = new Properties();
		FileInputStream i = null;
		try {
			i = new FileInputStream(CarParkingUtils.serverpath + "\\src\\main\\webapp\\conf\\configuration.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't able to load config file" + e);
		}
		try {
			p.load(i);
		} catch (IOException e) {
			System.out.println("Couldn't able to load config file" + e);
		}
		int slot = Integer.parseInt(p.get("numberofslot").toString());
		int slotSize = Integer.parseInt(p.get("numberofslotsize").toString());
		new CarParkingUtils().setSlot(slot);
		new CarParkingUtils().setSlotSize(slotSize);
		ScrCarParkingPOJO ArrscrObj[] = new ScrCarParkingPOJO[slot];
		for (int j = 0; j < slot; j++) {
			ScrCarParkingPOJO scrObj = new ScrCarParkingPOJO();
			char c = (char) (j + 65);
			scrObj.setAvailable(slotSize);
			scrObj.setFloor(Character.toString(c));
			scrObj.setCapacity(slotSize);
			ArrscrObj[j] = scrObj;
		}
		String redisIP = (String) p.get("REDISIP");
		String port = (String) p.get("REDISPORT");
		createRedisConnection(redisIP, port);
		new CarParkingUtils().setValueInRedis((Object) ArrscrObj);
		String[] cost = p.get("COST").toString().split(",");
		for(int k=0;k<cost.length;k++) {
			new CarParkingUtils().setCost(k,Integer.parseInt(cost[k]));	
		}
	}

	private void createRedisConnection(String redisIP, String port) {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://" + redisIP + ":" + port);
		RedissonClient redisson = Redisson.create(config);
		new CarParkingUtils().setRedisObj(redisson);
	}

}
