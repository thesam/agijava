package agijava.main;

import agijava.main.impl.Position;
import agijava.main.impl.AnimatedObject.LoopDirection;
import agijava.view.IView;


public interface IAnimatedObject {

	int getCurrentViewCel();

	IView getView();

	int getCurrentViewLoop();

	void setLoopFinishedFlag(int finishFlagNo);

	void setInSingleLoop(boolean b);

	void setSingleLoopDirection(LoopDirection forward);

	void setVisible(boolean b);

	int getDirection();

	void setMoving(boolean b);

	boolean isMoving();

	void setDirection(int var);

	int getNumber();

	void setX(int i);

	void setY(int i);

	boolean shouldBeUpdated();

	boolean isEgo();

	void setIgnoreBlocks(boolean b);

	void setMovingToDestination(boolean b);

	void setPixelStepSize(int stepsize);

	void setMoveFinishedFlagNo(int finishFlag);

	void setObserveObjects(boolean b);

	void setCurrentViewCel(int celNo);

	void setCurrentViewLoop(int loopNo);

	void setIsCycling(boolean b);

	void setPriority(int i);

	void setReverseCycle(boolean b);

	void setShouldBeUpdated(boolean b);

	void setView(int viewNo);

	public Position getCurrentPosition();

	void setPosition(Position newPos);

	void setDestination(Position position);

	boolean isMovingToDestination();

	Position getDestination();

	int getPixelStepSize();

	int getMoveFinishedFlagNo();

	boolean isCycling();

	boolean isReverseCycle();

	boolean isInSingleloop();

	LoopDirection getSingleLoopDirection();

	int getSingleLoopFinishFlag();

	boolean getIgnoreBlocks();

}
