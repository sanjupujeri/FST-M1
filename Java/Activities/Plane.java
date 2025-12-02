package activity1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Plane {

	private List<String> passengers;
	private int maxPassengers;
	private Date lastTakeOffTime;
	private Date lastLandingTime;

	public Plane(int maxPassengers) {
		this.maxPassengers = maxPassengers;
		this.passengers = new ArrayList<>();
	}

	public void onboard(String name) {
		if (passengers.size() < maxPassengers) {
			passengers.add(name);
		} else {
			System.out.println("Plane is full. Cannot add " + name);
		}
	}

	public Date takeOff() {
		lastTakeOffTime = new Date();
		return lastTakeOffTime;
	}

	public void land() {
		lastLandingTime = new Date();
		passengers.clear();
	}

	public Date getLastLandingTime() {
		return lastLandingTime;
	}

	public List<String> getPassengers() {
		return passengers;
	}
}
