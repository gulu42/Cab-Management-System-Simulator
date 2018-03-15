package iiitb.ess201a7.r16114;

import iiitb.ess201a7.a7base.*;
import java.util.*;
import java.lang.*;
public class Fleet16114 extends Fleet {
	public Fleet16114(String colour) {
		super(16114,colour);
	}
	private ArrayList<Car> carList = new ArrayList<Car>();
	@Override
	public void addCar(int speed) {
		carList.add(new Car16114(speed));
	}

	@Override
	public Car findNearestCar(Location loc) {
		Car temp = null;
		double dis = -1;
		for(Car c : carList){
			if(c.getStatus()==1){
				dis = this.getDist(c.getLocation(),loc);
				temp = c;
				break;
			}
		}

		for(int i = 0;i<carList.size();i++){
			if(this.getDist(carList.get(i).getLocation(),loc)<dis && carList.get(i).getStatus()==1 ){
				temp = carList.get(i);
				dis = this.getDist(carList.get(i).getLocation(),loc);
			}
		}
		return temp;
	}
	private double getDist(Location l1,Location l2){
		return Math.sqrt((l1.getX()-l2.getX())*(l1.getX()-l2.getX())+(l1.getY()-l2.getY())*(l1.getY()-l2.getY()));
	}
	@Override
	public ArrayList<Car> getCars(){
		return this.carList;
	}

}
