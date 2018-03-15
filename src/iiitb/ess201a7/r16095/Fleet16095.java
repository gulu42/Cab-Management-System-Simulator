package iiitb.ess201a7.r16095;
import java.util.*;
import iiitb.ess201a7.a7base.*;

public class Fleet16095 extends Fleet
{
    public ArrayList<Car16095> car_list = new ArrayList<Car16095>();

    public Fleet16095 (String colour)
    {
        super(16095,colour);
    }

    @Override
    public  void addCar(int speed)
    {
        Location loc = new Location(0,0);
        int id = Integer.parseInt("16095"+(car_list.size()+1));
        Car16095 car = new Car16095(id,speed);
        car.setLocation(loc);
        car.setStatus(1);
        car_list.add(car);
    }

    @Override
    public Car findNearestCar(Location loc)
    {
        int max = Integer.MAX_VALUE;
        Car nearest_car = null;
        for (Car16095 car: car_list)
        {
            if(car.distSqrd(loc) < max && car.status == 1)
            {
                nearest_car = car;
                max = car.distSqrd(loc);
            }
        }

        return nearest_car;
    }

        // v3 - added
    @Override
    public ArrayList<Car16095> getCars()
    {
        return car_list;
    }
}
