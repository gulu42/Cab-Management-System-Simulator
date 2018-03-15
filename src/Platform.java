import java.util.ArrayList;

import iiitb.ess201a7.a7base.*;

public class Platform {

	private ArrayList<Fleet> myFleet;

	// private double getDistance(Location l1,Location l2){
	// 	int y = l2.getY() - l1.getY();
	// 	int x = l2.getX() - l1.getX();
	//
	// 	return Math.sqrt((y*y) + (x*x));
	// }

	public Platform() {
		myFleet = new ArrayList<Fleet>();
	}

	public void addFleet(Fleet f) {
		myFleet.add(f);
	}

	public Car assignCar(Trip trip) {//takes closest car from every fleet and finds the closest there
		Car temp_car;
		ArrayList<Car> closeCars = new ArrayList<Car>();
		for(Fleet f : myFleet)
		{
			temp_car = f.findNearestCar(trip.getStart());
			if(temp_car != null)
				closeCars.add(temp_car);
		}

		//found the closest car from each fleet

		// double min = getDistance(trip.getStart(),closeCars.get(0).getLocation());

		if(closeCars.size() > 0)
		{
			int min = closeCars.get(0).distSqrd(trip.getStart());//can compare square of distance too
			int min_index = 0;
			// double temp;
			int temp;

			for(int i = 1 ; i < closeCars.size() ; i++)
			{
				// temp = getDistance(trip.getStart(),closeCars.get(i).getLocation());
				temp = closeCars.get(i).distSqrd(trip.getStart());
				if(temp < min)
				{
					min = temp;
					min_index = i;
				}
			}
			closeCars.get(min_index).assignTrip(trip);//did this here since it wasn't done in platform
			return closeCars.get(min_index);
		}
		System.out.println("Oops...no cars");//for debugging
		return null;
	}

	// returns list of all cars (in all the fleets) managed by this platform
	public ArrayList<Car> findCars() {
		ArrayList<Car> allCars = new ArrayList<Car>();
		for(Fleet f: myFleet)
		{
			allCars.addAll(f.getCars());
		}
		return allCars;
	}
}
