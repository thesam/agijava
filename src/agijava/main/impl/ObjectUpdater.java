package agijava.main.impl;

import java.util.List;

import agijava.main.IAnimatedObject;
import agijava.main.IGameState;
import agijava.main.IMovementCalculator;
import agijava.main.IObjectUpdater;
import agijava.main.impl.AnimatedObject.LoopDirection;
import agijava.picture.IPicture;
import agijava.picture.impl.Picture;
import agijava.view.ICel;
import agijava.view.ILoop;
import agijava.view.IView;

public class ObjectUpdater implements IObjectUpdater {

	private static final int GREENLINE_TRIGGER = 3;
	
	private IGameState gameState;
	private IMovementCalculator calculator;

	public ObjectUpdater(IGameState gameState, IMovementCalculator calculator) {
		this.gameState = gameState;
		this.calculator = calculator;
	}

	@Override
	public void updatePosition(IAnimatedObject movingObject) {
		Position newPos = calculateNewPosition(movingObject);
		if (!movingObject.isEgo()) {
			keepMovingObject(movingObject, newPos);
		} else {
			handleEgoMovement(movingObject, newPos,
					gameState.getCurrentPicture());
		}
	}

	@Override
	public void stopIfDestinationIsReached(IAnimatedObject movingObject) {
		if (movingObject.isMovingToDestination() && movingObject.getDestination().equals(movingObject.getCurrentPosition())) {
			stopMovingObject(movingObject);
		}
	}

	//TODO: Unit test
	@Override
	public void updateLoopAndCel(IAnimatedObject movingObject) {
		IView view = movingObject.getView();
		if (view == null) {
			return;
		}
		int noLoops = view.getLoops().size();
		if (noLoops <= 1) {
			return;
		}
		int currentLoop = movingObject.getCurrentViewLoop();

		int newLoop = getNewLoop(movingObject.getDirection(), noLoops, currentLoop);
		movingObject.setCurrentViewLoop(newLoop);
		if (newLoop != currentLoop) {
			movingObject.setCurrentViewCel(0);
		} else {
			if (movingObject.isCycling()) {
				cycleCel(movingObject, currentLoop);
			}
		}
	
	}

	@Override
	public void updateSingleLoop(IAnimatedObject animatedObject) {
		if (animatedObject.isInSingleloop()) {
			IView view = animatedObject.getView();
			int loopIndex = animatedObject.getCurrentViewLoop();
			int celIndex = animatedObject.getCurrentViewCel();
			int lastCelIndex = getLastCelIndex(view, loopIndex);
			if (animatedObject.getSingleLoopDirection() == LoopDirection.FORWARD
					&& celIndex < lastCelIndex) {
				animatedObject.setCurrentViewCel(celIndex + 1);
			} else if (animatedObject.getSingleLoopDirection() == LoopDirection.REVERSE
					&& celIndex > 0) {
				animatedObject.setCurrentViewCel(celIndex - 1);
			} else {
				gameState.setFlag(animatedObject.getSingleLoopFinishFlag());
				animatedObject.setInSingleLoop(false);
			}
		}
	}

	private void cycleCel(IAnimatedObject movingObject, int currentLoop) {
		int noCels = movingObject.getView().getLoops().get(currentLoop)
				.getCels().size();
		int currentViewCel = movingObject.getCurrentViewCel();
		if (movingObject.isReverseCycle()) {
			rotateCelForward(movingObject, noCels, currentViewCel);
		} else {
			rotateCelBackward(movingObject, noCels, currentViewCel);
		}
	}

	private int getNewLoop(int direction, int noLoops,
			int currentLoop) {
		int newLoop = 0;
	
		switch (direction) {
		case Directions.STOP:
			newLoop = currentLoop;
			break;
		case Directions.NORTH:
			if (noLoops >= 4) {
				newLoop = 3;
			}
			break;
		case Directions.NORTHEAST:
			newLoop = 0;
			break;
		case Directions.EAST:
			newLoop = 0;
			break;
		case Directions.SOUTHEAST:
			newLoop = 0;
			break;
		case Directions.SOUTH:
			if (noLoops >= 4) {
				newLoop = 2;
			}
			break;
		case Directions.SOUTHWEST:
			newLoop = 1;
			break;
		case Directions.WEST:
			newLoop = 1;
			break;
		case Directions.NORTHWEST:
			newLoop = 1;
			break;
		}
		return newLoop;
	}

	private int getLastCelIndex(IView view, int loopIndex) {
		List<ILoop> loops = view.getLoops();
		return loops.get(loopIndex).getCels().size() - 1;
	}

	private void rotateCelBackward(IAnimatedObject movingObject, int noCels,
			int currentViewCel) {
		int nextCel = currentViewCel - 1;
		if (nextCel < 0) {
			nextCel = noCels - 1;
		}
		movingObject.setCurrentViewCel(nextCel);
	}

	private void rotateCelForward(IAnimatedObject movingObject, int noCels,
			int currentViewCel) {
		movingObject.setCurrentViewCel((currentViewCel + 1)
				% noCels);
	}

	private Position calculateNewPosition(IAnimatedObject movingObject) {
		Position newPos = new Position(0,0);
		if (movingObject.isMovingToDestination()) {
			Position destinationPosition = movingObject.getDestination();
			newPos = calculator.calculateNewPosition(movingObject.getCurrentPosition(), destinationPosition, movingObject.getPixelStepSize());
		} else {
			newPos = calculator.calculateNewPosition(movingObject.getCurrentPosition(),movingObject.getDirection());
		}
		return newPos;
	}

	private void stopMovingObject(IAnimatedObject movingObject) {
		movingObject.setMoving(false);
		movingObject.setMovingToDestination(false);
		gameState.setFlag(movingObject.getMoveFinishedFlagNo());
	}

	private void handleEgoMovement(IAnimatedObject ego, Position newPos, IPicture currentPicture) {
		if (isOutsidePicture(newPos, currentPicture)) {
			ego.setMoving(false);
		} else {
			if (viewIsOnBlockingLineInPicture(ego, newPos,
					currentPicture)) {
				ego.setMoving(false);
			} else {
				keepMovingObject(ego, newPos);
				checkIfObjectIsOnTriggerLine(ego, newPos,
						currentPicture);
			}
		}
	}
	
	private void checkIfObjectIsOnTriggerLine(IAnimatedObject movingObject,
			Position newPos, IPicture currentPicture) {
		if (viewIsOnTriggerLineInPicture(movingObject, newPos,
				currentPicture)) {
			gameState
					.setFlag(GREENLINE_TRIGGER);
		} else {
			gameState
					.reset(GREENLINE_TRIGGER);
		}
	}

	private void keepMovingObject(IAnimatedObject movingObject, Position newPos) {
		movingObject.setPosition(newPos);
	}

	private boolean viewIsOnTriggerLineInPicture(IAnimatedObject movingObject,
			Position newPos, IPicture currentPicture) {
		return isViewBottomLineOnPriority(currentPicture,
				movingObject, newPos,
				Picture.PRIORITY_TRIGGER);
	}

	private boolean viewIsOnBlockingLineInPicture(IAnimatedObject movingObject,
			Position newPos, IPicture currentPicture) {
		//TODO: Blue lines are conditional, check the conditions..
		return isViewBottomLineOnPriority(currentPicture,
				movingObject, newPos,
				Picture.PRIORITY_BLOCK) || isViewBottomLineOnPriority(currentPicture, movingObject, newPos, Picture.PRIORITY_CONDITIONAL_BLOCK) && !movingObject.getIgnoreBlocks();
	}
	
	private boolean isViewBottomLineOnPriority(IPicture currentPicture,
			IAnimatedObject movingObject, Position bottomLeft,
			int priority) {
		boolean isTouching = false;
		ILoop currentLoop = movingObject.getView().getLoops()
				.get(movingObject.getCurrentViewLoop());
		ICel currentCel = currentLoop.getCels().get(
				movingObject.getCurrentViewCel());
		for (int x = 0; x < currentCel.getWidth(); x++) {
			if (currentPicture.getPrioColorAt(bottomLeft.getX() + x, bottomLeft.getY()) == priority) {
				isTouching = true;
				break;
			}
		}
		return isTouching;
	}

	private boolean isOutsidePicture(Position newPos, IPicture currentPicture) {
		return newPos.getX() >= currentPicture.getWidth()
				|| newPos.getY() >= currentPicture.getHeight() || newPos.getX() < 0 || newPos.getY() < 0;
	}

}
