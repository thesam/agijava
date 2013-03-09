package agijava.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovementCalculatorTest {
	
	private MovementCalculator movementCalculator;

	@Test
	public void canBeCreated() throws Exception {
		aMovementCalculator();
		assertNotNull(movementCalculator);
	}
	
	@Test
	public void movesOneCoordinateInXIfNotAtDestination() throws Exception {
		aMovementCalculator();
		
		int pixelStepSize = 0;
		
		Position currentPosition = new Position(0, 0);
		Position destinationPosition = new Position(2, 2);
		Position newPos = movementCalculator.calculateNewPosition(currentPosition, destinationPosition, pixelStepSize);
		assertEquals(1,newPos.getX());
		
		currentPosition = new Position(4,0);
		newPos = movementCalculator.calculateNewPosition(currentPosition, destinationPosition, pixelStepSize);
		assertEquals(3,newPos.getX());
		
		currentPosition = new Position(2,0);
		newPos = movementCalculator.calculateNewPosition(currentPosition, destinationPosition, pixelStepSize);
		assertEquals(2,newPos.getX());
		
	}
	
	@Test
	public void movesOneCoordinateInYIfNotAtDestination() throws Exception {
		aMovementCalculator();
		
		int pixelStepSize = 0;
		
		Position currentPosition = new Position(0, 0);
		Position destinationPosition = new Position(2, 2);
		Position newPos = movementCalculator.calculateNewPosition(currentPosition, destinationPosition, pixelStepSize);
		assertEquals(1,newPos.getY());
		
		currentPosition = new Position(0,4);
		newPos = movementCalculator.calculateNewPosition(currentPosition, destinationPosition, pixelStepSize);
		assertEquals(3,newPos.getY());
		
		currentPosition = new Position(0,2);
		newPos = movementCalculator.calculateNewPosition(currentPosition, destinationPosition, pixelStepSize);
		assertEquals(2,newPos.getY());
		
	}
	
	@Test
	public void canMoveNorth() throws Exception {
		aMovementCalculator();
		Position currentPosition = new Position(0, 0);
		Position newPosition = movementCalculator.calculateNewPosition(currentPosition, Directions.NORTH);
		assertEquals(newPosition.getY(), -1);
	}
	
	private void aMovementCalculator() {
		movementCalculator = new MovementCalculator();
	}
}
