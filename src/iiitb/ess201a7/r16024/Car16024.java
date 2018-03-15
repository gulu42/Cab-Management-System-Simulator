package iiitb.ess201a7.r16024;

import iiitb.ess201a7.a7base.*;

class Car16024 extends Car {

	private Location myLocation;
	private int myStatus;
	private Trip myTrip;

	public Car16024(int fid,int speed) {
		super(fid,speed);
		this.myTrip = null;
		this.myStatus = Idle;
		this.myLocation = new Location(240,60);
	}

	@Override
	public void setLocation(Location l) {
		this.myLocation = l;
	}

	@Override
	public Location getLocation() {
		return this.myLocation;
	}

	@Override
	public void setStatus(int s) {
		this.myStatus = s;
	}

	@Override
	public int getStatus() {
		return this.myStatus;
	}

	@Override
	public void assignTrip(Trip trip) {
		this.myTrip = trip;
		this.setStatus(Booked);
	}

	public Trip getTrip() {
		return this.myTrip;
	}

	@Override
	public Location getStart() {
		return this.myTrip.getStart();
	}

	@Override
	public Location getDest() {
		return this.myTrip.getDest();
	}

	@Override
	public int distSqrd(Location l)
	{
		return (this.myLocation.getX() - l.getX())*(this.myLocation.getX() - l.getX()) + (this.myLocation.getY() - l.getY())*(this.myLocation.getY() - l.getY());
	}

	@Override
	public void updateLocation(double deltaT) {
		int x = myLocation.getX(),y = myLocation.getY();
		if(myStatus == Idle)
		{
			return;
		}
		else if(myStatus == Booked)
		{
			int xe = myTrip.getStart().getX();
			int ye = myTrip.getStart().getY();
			double dist = Math.hypot(xe-x,ye-y);
			if(maxSpeed*deltaT >= dist)
			{
				this.setStatus(OnTrip);
				x = xe;
				y = ye;
			}
			else
			{
				x += ((xe-x)/dist)*maxSpeed*deltaT;
				y += ((ye-y)/dist)*maxSpeed*deltaT;
			}
		}
		else
		{
			int xe = myTrip.getDest().getX();
			int ye = myTrip.getDest().getY();
			double dist = Math.hypot(xe-x,ye-y);
			if(maxSpeed*deltaT >= dist)
			{
				this.setStatus(Idle);
				x = xe;
				y = ye;
			}
			else
			{
				x += ((xe-x)/dist)*maxSpeed*deltaT;
				y += ((ye-y)/dist)*maxSpeed*deltaT;
			}
		}
		myLocation.set(x,y);
	}
}
