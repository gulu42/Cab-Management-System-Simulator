package iiitb.ess201a7.r16095;
import java.util.*;
import iiitb.ess201a7.a7base.*;
import java.lang.Math.*;

public class Car16095 extends Car {

    public int status;
    public Trip tour = null;
    public Location location;

    Car16095(int id,int speed)
    {
        super(id,speed);
    }

    @Override
    public int distSqrd(Location loc) 
    {
        return (loc.getX()-this.location.getX())*(loc.getX()-this.location.getX())+(loc.getY()-this.location.getY())*(loc.getY()-this.location.getY());
    }

    @Override
    public void setLocation(Location loc)
    {
        this.location = loc;
    }

    @Override
    public Location getLocation()
    {
        return location;
    }

    @Override
    public void setStatus(int stat)
    {
        this.status = stat;
    }

    @Override
    public int getStatus()
    {
        return status;
    }

    @Override
    public void assignTrip(Trip trip)
    {
        this.tour = trip;

        if (location.equals(trip.getStart()))
            status=3;
        else
            status=2;
    }

    @Override
    public Trip getTrip()
    {
        if (status != 1)
            return tour;
        return null;
    }

    // return location of start of trip (where customer is to be picked up)
    // null if idle
    @Override
    public Location getStart()
    {
        if (status != 1)
            return tour.getStart();
        
        return null;
    }

    // return location of end of trip (where customer is to be dropped off)
    // null if idle
    @Override
    public Location getDest()
    {
        if (status != 1)
            return tour.getDest();
        
        return null;
    }

    // v3 - parameter is now double instead of int
    public void updateLocation(double deltaT)
    {
      //System.out.println(stat+" "+this.getId());
        if(status != 1)
        {
            double dist = deltaT * getSpeed();
            double x,y;
            Location loc = new Location(0,0);
            if (status == 2)
            {
                double fd = java.lang.Math.sqrt(distSqrd(getStart()));
                if (dist < fd)
                {
                    x = (((dist*getStart().getX()) + ((fd-dist)*location.getX())) / fd);
                    y = (((dist*getStart().getY()) + ((fd-dist)*location.getY())) / fd);
                    loc.set((int)x, (int)y);
                    setLocation(loc);
                }
                else
                {
                    setLocation(getStart());
                    dist = dist - fd;
                    status = 3;
                }
            }

            if (status == 3)
            {
                double fd = java.lang.Math.sqrt(distSqrd(getDest()));
                if (dist < fd)
                {
                    x = (((dist*getDest().getX()) + ((fd-dist)*location.getX())) / fd);
                    y = (((dist*getDest().getY()) + ((fd-dist)*location.getY())) / fd);
                    loc.set((int)x, (int)y);
                    setLocation(loc);
                }
                else
                {
                    setLocation(getDest());
                    status = 1;
                }
            }
        }

    }

}
