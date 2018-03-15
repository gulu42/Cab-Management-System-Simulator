package iiitb.ess201a7.r16114;

import iiitb.ess201a7.a7base.*;
import static java.lang.Math.pow;
class Car16114 extends Car {
	private static int id = 161141;
	private static int random = 3;
	private Location loc;
	private int status;
	private Trip t;
	public Car16114(int speed) {
		super(id,speed);
		id+=1;
		this.setLocation(new Location(random*3,random));
		this.setStatus(1);
		random+=3;
	}

	@Override
	public void setLocation(Location l) {
		loc = l;

	}
  @Override
  public int distSqrd(Location loc){
    return (int)(pow(this.getLocation().getX()-loc.getX(),2)+pow(this.getLocation().getY()-loc.getY(),2));
  }
	@Override
	public Location getLocation() {
		return this.loc;
	}

	@Override
	public void setStatus(int s) {
		this.status = s;

	}

	@Override
	public int getStatus() {
		return this.status;
	}

	@Override
	public void assignTrip(Trip trip) {
		this.t =  trip;
		this.status = 2;

	}

	@Override
	public Location getStart() {
		return t.getStart();
	}

	@Override
	public Location getDest() {
		return t.getDest();
	}
  @Override
  public Trip getTrip(){
    return this.t;
  }
	@Override
	public void updateLocation(double deltaT) {
		double tempX=-1;
		double tempY=-1;
    int cross = 0;
		if (this.getStatus()!=1){
			if(this.getStatus()==2){
        double hyp = pow(distSqrd(this.getStart()),0.5);
        double sin = (this.getStart().getY() - this.getLocation().getY())/hyp;
        double cos = (this.getStart().getX() - this.getLocation().getX())/hyp;
        tempX = this.getLocation().getX() + deltaT*this.getSpeed()*cos;
        tempY = this.getLocation().getY() + deltaT*this.getSpeed()*sin;
        if(pow(distSqrd(new Location((int)tempX,(int)tempY)),0.5)>pow(distSqrd(this.getStart()),0.5)){
          tempX = this.getStart().getX();
          tempY = this.getStart().getY();
        }
      }
      else{
        double hyp = pow(distSqrd(this.getDest()),0.5);
        double sin = (this.getDest().getY() - this.getLocation().getY())/hyp;
        double cos = (this.getDest().getX() - this.getLocation().getX())/hyp;
        tempX = this.getLocation().getX() + deltaT*this.getSpeed()*cos;
        tempY = this.getLocation().getY() + deltaT*this.getSpeed()*sin;
        if(pow(distSqrd(new Location((int)tempX,(int)tempY)),0.5)>pow(distSqrd(this.getDest()),0.5))
				{
          tempX = this.getDest().getX();
          tempY = this.getDest().getY();
        }
      }

			loc.set((int)tempX,(int)tempY);
		}
		if (this.getStatus()==2 && this.getLocation().getX()==this.getStart().getX() && this.getLocation().getY()==this.getStart().getY()){
			this.setStatus(3);
		}
		if(this.getStatus()==3 && this.getLocation().getX()==this.getDest().getX() && this.getLocation().getY()==this.getDest().getY()){
			this.setStatus(1);
		}
	}



}
