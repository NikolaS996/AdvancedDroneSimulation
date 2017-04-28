package drones;

import java.util.List;

import space.FlySpace;

public class Drone implements ClassicDrone{
	
	protected FlySpace flySpace;
	private int [] droneCoordinates;

	public Drone(Cube outerBoundaries, Cube innerBoundaries, int[] droneStartCoordinates)
	{
		flySpace = new FlySpace(outerBoundaries, innerBoundaries);
		droneCoordinates = droneStartCoordinates;
	}
	
	public Drone(Cube outerBoundaries, Cube innerBoundaries, List<Cube> obstacles)
	{
		flySpace = new FlySpace(outerBoundaries, innerBoundaries, obstacles);
	}
	
	public Drone(Cube outerBoundaries, Cube innerBoundaries)
	{
		flySpace = new FlySpace(outerBoundaries, innerBoundaries);
	}
	
	public Drone()
	{
		
	}

	protected boolean validateDronePositionAfter(String command)
	{
		int [] newCoordinates = new int[3];
		switch (command) {
		case "up":
			newCoordinates = new int [] {droneCoordinates[0], droneCoordinates[1] + 1, droneCoordinates[2]};
			break;
		case "down":
			newCoordinates = new int [] {droneCoordinates[0], droneCoordinates[1] - 1, droneCoordinates[2]};
			break;
		case "left":
			newCoordinates = new int [] {droneCoordinates[0] - 1, droneCoordinates[1], droneCoordinates[2]};
			break;
		case "right":
			newCoordinates = new int [] {droneCoordinates[0] + 1, droneCoordinates[1], droneCoordinates[2]};
			break;
		case "back":
			newCoordinates = new int [] {droneCoordinates[0], droneCoordinates[1], droneCoordinates[2] + 1};
			break;
		case "forth":
			newCoordinates = new int [] {droneCoordinates[0], droneCoordinates[1], droneCoordinates[2] - 1};
			break;
		}
		return !flySpace.getInnerBoundaries().checkCoordinates(newCoordinates) &&
				flySpace.getOuterBoundaries().checkCoordinates(newCoordinates);
	}
	
	@Override
	public String moveUp() {
		if(validateDronePositionAfter("up"))
		{
			droneCoordinates[1] += 1;
		}
		return getFormatedCoordinates();
	}

	@Override
	public String moveDown() {
		if(validateDronePositionAfter("down"))
		{
			droneCoordinates[1] -= 1;
		}
		return getFormatedCoordinates();
	}

	@Override
	public String moveLeft() {
		if(validateDronePositionAfter("left"))
		{
			droneCoordinates[0] -= 1;
		}
		return getFormatedCoordinates();
	}

	@Override
	public String moveRight() {
		if(validateDronePositionAfter("right"))
		{
			droneCoordinates[0] += 1;
		}
		return getFormatedCoordinates();
	}

	@Override
	public String moveBack() {
		if(validateDronePositionAfter("back"))
		{
			droneCoordinates[2] += 1;
		}
		return getFormatedCoordinates();
	}

	@Override
	public String moveForth() {
		if(validateDronePositionAfter("forth"))
		{
			droneCoordinates[2] -= 1;
		}
		return getFormatedCoordinates();
	}
	
	public String getFormatedCoordinates()
	{		
		return "Drone position: (" + Integer.toString(droneCoordinates[0]) + ","
				+ Integer.toString(droneCoordinates[1]) + ","  
				+ Integer.toString(droneCoordinates[2]) + ")";	
	}
}
