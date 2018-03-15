import iiitb.ess201a7.a7base.*;


public class TextDisplay extends Display{

	// needed for 2-way communication between App and Display

	App app;

	public TextDisplay() {
		//is blank for now
	}

	public void setApp(App a) {
		app = a;
	}
	// for initial testing, write to console

	public void draw(Car car) {

		System.out.println("Car"+car.getId() + " at " + car.getLocation());

	}

	public void drawLine(Location a, Location b) {
		System.out.println("Line from " + a + " to " + b);
	}

	public void requestTrip(Location start, Location dest) {
		app.handleTripRequest(start,dest);
	}
}
