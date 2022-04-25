package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.json.simple.JSONObject;

import carParkingUtils.CarParking;
import carParkingUtils.CarParkingUtils;
import carparking.CarParkingAction;

public class Processing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Processing() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		start(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		start(request, response);
	}

	private void start(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String action = request.getParameter("action");
		if (action == null) {
			action = request.getParameter("handleid");
		}
		System.out.println(action);
		CarParking carParkingObj = new CarParkingAction();
		if ("checkIn".equals(action)) {
			String vechNo = (String) request.getParameter("VechicleNumber");
			String type = (String) request.getParameter("type");
			String lot = (String) request.getParameter("lot");
			String time = (String) request.getParameter("time");
			JSONObject json = carParkingObj.CheckIn(vechNo, type, lot, time);
			if ((boolean) json.get("result")) {
				request.setAttribute("result",
						"Vechicle is parked at " + lot + (Integer.parseInt((String) json.get("slot")) + 1));
				request.getRequestDispatcher("/checkin.jsp").forward(request, response);
			} else {
				request.setAttribute("result", "The lot " + lot + "is full");
				request.getRequestDispatcher("/checkin.jsp").forward(request, response);
			}
		} else if ("checkout".equals(action)) {
			String vechNo = (String) request.getParameter("VechicleNumber");
			String checkoutTime = (String) request.getParameter("checkouttime");
			JSONObject json = carParkingObj.CheckOut(vechNo, checkoutTime);
			if ((boolean) json.get("result")) {
				request.setAttribute("result", "Sccessfully checked out");
				request.setAttribute("cost", "Cost for the " + json.get("time") + "hours" + json.get("cost"));
				request.getRequestDispatcher("/checkout.jsp").forward(request, response);
			} else {
				request.setAttribute("result", "Error occurred");
				request.getRequestDispatcher("/checkout.jsp").forward(request, response);
			}
		} else if ("findvech".equals(action)) {
			String vechNo = (String) request.getParameter("VechicleNumber");
			JSONObject json = carParkingObj.FindMyVechicle(vechNo);
			request.setAttribute("result", json.get("result"));
			request.setAttribute("history", json.get("history"));
			request.setAttribute("submitted", true);
			request.getRequestDispatcher("/FindMyVechicle.jsp").forward(request, response);
			
		} else if ("showlot".equals(action)) {
			System.out.println(action);
			JSONObject json = carParkingObj.showLot();
			request.setAttribute("obj", json.get("obj"));
			request.getRequestDispatcher("/showlots.jsp").forward(request, response);

		} else if ("onload".equals(action)) {
			System.out.println(action);
			String[] data = new CarParkingUtils().getSolt().split(":");
			response.getWriter().print(
					"Total Number of Floors :" + data[0] + "      " + "Total number of slot in each floor " + data[1]);

		}

	}

}
