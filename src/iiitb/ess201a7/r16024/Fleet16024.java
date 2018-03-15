package iiitb.ess201a7.r16024;
import java.util.*;
import iiitb.ess201a7.a7base.*;

public class Fleet16024 extends Fleet {

	private ArrayList<Car> cars;
	private int carid;

	public Fleet16024(String colour) {
		super(16024,colour);
		cars = new ArrayList<Car>();
		carid = 1;
	}

	@Override
	public void addCar(int speed) {
		int temp  = this.getId();
		int x = carid;
		while(x > 0)
		{
			temp = temp*10;
			x/=10;
		}
		temp += carid;
		cars.add(new Car16024(temp,speed));
		carid++;
	}

	@Override
	public Car findNearestCar(Location loc) {
		Car min = null;
		double temp = Double.POSITIVE_INFINITY;
		for(Car c: cars)
		{
			if(c.getStatus() == 1)
			{
				if(Math.sqrt(c.distSqrd(loc)) < temp)
				{
					temp = Math.sqrt(c.distSqrd(loc));
					min = c;
				}
			}
		}
		return min;
	}

	public ArrayList<? extends Car> getCars(){
		return cars;
	}
}
