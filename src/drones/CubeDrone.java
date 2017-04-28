package drones;

import java.util.List;

public class CubeDrone extends Drone{
	
	Cube drone = null;
	Cube newCube = null;
	private boolean obstacleHit = false;
	private int moveCounter;
		
	public CubeDrone(Cube outerBoundaries, Cube innerBoundaries, int [] droneStartCoordinates, int dronSide)
	{
		super(outerBoundaries, innerBoundaries);
		this.drone = new Cube(droneStartCoordinates, dronSide);
	}
	
	public CubeDrone(Cube outerBoundaries, Cube innerBoundaries, int [] droneStartCoordinates, int dronSide, List<Cube> obstacles)
	{
		super(outerBoundaries, innerBoundaries, obstacles);
		this.drone = new Cube(droneStartCoordinates, dronSide);
	}

	@Override
	protected boolean validateDronePositionAfter(String command)
	{
		int[] minCoordinates = drone.getMinCoordinates();
		int droneSide = drone.getCubeSideLength();
		newCube = new Cube(minCoordinates, droneSide);

		switch (command) {
		case "up":
			newCube.increaseY(1);
			break;
		case "down":
			newCube.decreaseY(1);
			break;
		case "left":
			newCube.decreaseX(1);
			break;
		case "right":
			newCube.increaseX(1);
			break;
		case "back":
			newCube.increaseZ(1);
			break;
		case "forth":
			newCube.decreaseZ(1);
			break;
		}
		return !super.flySpace.getInnerBoundaries().checkCubeIntersection(newCube) ||
			   !super.flySpace.getOuterBoundaries().checkCubeIntersection(newCube);
	}
	
	
	private boolean getAroundObstacle(String initialCommand)
	{		
		switch(initialCommand)
		{
		case "up": 
			if(validateDronePositionAfter("left"))
			{
				drone.decreaseX(1);
				if(validateDronePositionAfter("up"))
				{
					drone.increaseY(1);
					return true;
				}
			}
			if(validateDronePositionAfter("right"))
			{
				drone.increaseX(1);
				if(validateDronePositionAfter("up"))
				{
					drone.increaseY(1);
					return true;
				}
			}
			if(validateDronePositionAfter("forth"))
			{
				drone.decreaseZ(1);
				if(validateDronePositionAfter("up"))
				{
					drone.increaseY(1);
					return true;
				}
			}
			if(validateDronePositionAfter("back"))
			{
				drone.increaseZ(1);
				if(validateDronePositionAfter("up"))
				{
					drone.increaseY(1);
					return true;
				}
		}
		break;
		case "down":
			break;
		
		}
		
		return false;
	}
	
	
	@Override
	public String moveUp() {
		if(validateDronePositionAfter("up"))
		{
			drone.getMinCoordinates()[1] += 1;
			drone.getMaxCoordinates()[1] += 1;
		}
		return drone.toString();
	}

	@Override
	public String moveDown() {
		if(validateDronePositionAfter("down"))
		{
			drone.getMinCoordinates()[1] -= 1;
			drone.getMaxCoordinates()[1] -= 1;
		}
		return drone.toString();
	}

	@Override
	public String moveLeft() {
		if(validateDronePositionAfter("left"))
		{
			drone.getMinCoordinates()[0] -= 1;
			drone.getMaxCoordinates()[0] -= 1;
		}
		return drone.toString();
	}

	@Override
	public String moveRight() {
		if(validateDronePositionAfter("right"))
		{
			drone.getMinCoordinates()[0] += 1;
			drone.getMaxCoordinates()[0] += 1;
		}
		return drone.toString();
	}

	@Override
	public String moveBack() {
		if(validateDronePositionAfter("back"))
		{
			drone.getMinCoordinates()[2] += 1;
			drone.getMaxCoordinates()[2] += 1;
		}
		return drone.toString();
	}

	@Override
	public String moveForth() {
		if(validateDronePositionAfter("forth"))
		{
			drone.getMinCoordinates()[2] -= 1;
			drone.getMaxCoordinates()[2] -= 1;
		}
		return drone.toString();
	}
	
}
