package agijava.main;

import agijava.main.impl.Position;


public interface IMovementCalculator {

	Position calculateNewPosition(Position position, Position position2,
			int pixelStepSize);

	Position calculateNewPosition(Position currentPosition, int direction);


}
