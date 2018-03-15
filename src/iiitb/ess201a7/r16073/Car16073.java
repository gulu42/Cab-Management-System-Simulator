package iiitb.ess201a7.r16073;
import iiitb.ess201a7.a7base.*;

public class Car16073 extends Car{
	private Location location;
	private int status = 1;
	private Trip _trip;
	public Car16073(int fid,int speed) {
		super(fid,speed);
	}

	@Override
	public int distSqrd(Location loc) {
		return (int)(Math.pow((loc.getX()-location.getX()), 2) + Math.pow((loc.getY()-location.getY()), 2));
	}

	@Override
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		location = l;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public void setStatus(int s) {
		// TODO Auto-generated method stub
		status = s;
		
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void assignTrip(Trip trip) {
		// TODO Auto-generated method stub
		status = 2;
		_trip = trip;
		
	}
	@Override
	public Trip getTrip(){
		if(status!=1){
			return _trip;
		}
		else{
			return null;
		}
	}

	@Override
	public Location getStart() {
		// TODO Auto-generated method stub
		if(status!=1){
			return _trip.getStart();
		}
		else{
			return null;
		}
	}

	@Override
	public Location getDest() {
		// TODO Auto-generated method stub
		if(status!=1){
			return _trip.getDest();
		}
		else{
			return null;
		}
	}

	@Override
	public void updateLocation(double deltaT) {
		// TODO Auto-generated method stub
		if(status==2){
			if(Math.pow((deltaT*maxSpeed), 2)>=distSqrd(_trip.getStart())){
				status = 3;
				location = _trip.getStart();
			}
			else{
				int x = (int) (location.getX() + (deltaT*maxSpeed*((_trip.getStart().getX()-location.getX())/(Math.sqrt(distSqrd(_trip.getStart()))))));
				int y = (int) (location.getY() + (deltaT*maxSpeed*((_trip.getStart().getY()-location.getY())/(Math.sqrt(distSqrd(_trip.getStart()))))));
				location.set(x, y);
			}
		}
		else {
			if(status==3){
				if(Math.pow((deltaT*maxSpeed), 2)>=distSqrd(_trip.getDest())){
					status = 1;
					location = _trip.getDest();
			}
			else{
				int x = (int) (location.getX() + (deltaT*maxSpeed*((_trip.getDest().getX()-location.getX())/(Math.sqrt(distSqrd(_trip.getDest()))))));
				int y = (int) (location.getY() + (deltaT*maxSpeed*((_trip.getDest().getY()-location.getY())/(Math.sqrt(distSqrd(_trip.getDest()))))));
				location.set(x, y);
			}
			
		}
		}
		
	}

	

}
