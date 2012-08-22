package agijava.main.impl;

import agijava.main.IMovementCalculator;

public class MovementCalculator implements IMovementCalculator {

	public Position calculateNewPosition(Position currentPosition,
			Position destinationPosition, int pixelStepSize) {
		int newX = calculateNewX(currentPosition, destinationPosition,
				pixelStepSize);
		int newY = calculateNewY(currentPosition, destinationPosition,
				pixelStepSize);
		return new Position(newX, newY);
	}

	@Override
	public Position calculateNewPosition(Position currentPosition, int direction) {
		Position newPos = currentPosition;
		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		switch (direction) {
		case Directions.STOP:
			break;
		case Directions.NORTH:
			newPos = new Position(currentX, currentY - 1);
			break;
		case Directions.NORTHEAST:
			break;
		case Directions.EAST:
			newPos = new Position(currentX + 1, currentY);
			break;
		case Directions.SOUTHEAST:
			break;
		case Directions.SOUTH:
			newPos = new Position(currentX, currentY + 1);
			break;
		case Directions.SOUTHWEST:
			break;
		case Directions.WEST:
			newPos = new Position(currentX - 1, currentY);
			break;
		case Directions.NORTHWEST:
			break;
		}
		return newPos;
	}

	private int calculateNewX(Position currentPosition,
			Position destinationPosition, int pixelStepSize) {
		// TODO: Do it properly
		if (currentPosition.getX() < destinationPosition.getX()) {
			return currentPosition.getX() + 1;
		} else if (currentPosition.getX() > destinationPosition.getX()) {
			return currentPosition.getX() - 1;
		} else {
			return currentPosition.getX();
		}
	}

	private int calculateNewY(Position currentPosition,
			Position destinationPosition, int pixelStepSize) {
		// TODO: Do it properly
		if (currentPosition.getY() < destinationPosition.getY()) {
			return currentPosition.getY() + 1;
		} else if (currentPosition.getY() > destinationPosition.getY()) {
			return currentPosition.getY() - 1;
		} else {
			return currentPosition.getY();
		}
	}

}
