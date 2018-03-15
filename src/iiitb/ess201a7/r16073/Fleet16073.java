package iiitb.ess201a7.r16073;
import java.util.ArrayList;
//import java.lang.Math;

import iiitb.ess201a7.a7base.*;

public class Fleet16073 extends Fleet{
	int i = 1,id,x=0,y=0;
	ArrayList<Car16073> car_list = new ArrayList<Car16073>();
	
	public Fleet16073(String colour) {
		super(16073,colour);
	}
	
	@Override
	public void addCar(int speed) {
		int id = (16073*no_of_digit_in_i(i)*10) + i;
		Car16073 car = new Car16073(id,speed);
		i++;
		car_list.add(car);
		car.setLocation(new Location(x,y));
	}

	@Override
	public Car findNearestCar(Location loc) {
		Car min = null;
		double min_distance = Integer.MAX_VALUE;
		for(Car c : car_list){
			if(c.getStatus()==1){
				if(min_distance>c.distSqrd(loc)){
					min = c;
					min_distance = c.distSqrd(loc);
				}
			}
		}
		return min;
	}
	
	@Override
	public ArrayList<? extends Car> getCars(){
		return car_list;
	}
	
	private int no_of_digit_in_i(int i){
		int j = i;
		int count = 0;
		while(j>0){
			if(j>10){
				j = j/10;
				count++;
			}
			else{
				count++;
				j = 0;
			}
		}
		return count;
	}
	
	/*private double distance(Location a,Location b){
		return Math.sqrt(Math.pow((a.getX()-b.getX()), 2) + Math.pow((a.getY()-b.getY()), 2));
	}*/
}
