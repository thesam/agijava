package agijava.main;

import agijava.view.View;

public class AnimatedObject {
	private boolean observeObjects;
	private ViewRepository viewRepository;
	private View currentView;
	private boolean visible;
	private Position currentPosition;
	private boolean isMoving;
	private int pixelStepSize;
	private int finishFlagNo;
	private int currentViewCel;
	private int currentViewLoop;
	private boolean shouldBeUpdated = true;
	private int loopFinishedFlagNo;
	private boolean inSingleLoop;
	private int direction;
	private boolean movingToDestination;
	private boolean isCycling;
	private boolean ego;
	private LoopDirection singleLoopDirection;
	private int number;
	private boolean reverseCycle;
	private boolean ignoreBlocks;
	private int prio = -1;
	private Position destination;
	
	public enum LoopDirection {FORWARD,REVERSE};

	public AnimatedObject(ViewRepository viewRepository) {
		this.visible = false;
		this.viewRepository = viewRepository;
		currentPosition = new Position(0,0);
	}

	public void setObserveObjects(boolean b) {
		this.observeObjects = b;
	}

	public Position getCurrentPosition() {
		return this.currentPosition;
	}

	public void setView(int viewNo) {
		this.currentView = viewRepository.getView(viewNo);
	}

	public void setVisible(boolean b) {
		this.visible = b;
	}

	public boolean isVisible() {
		return visible;
	}

	public View getView() {
		return this.currentView;
	}

	public int getCurrentViewLoop() {
		return this.currentViewLoop;
	}

	public int getCurrentViewCel() {
		return this.currentViewCel;
	}

	public boolean isMoving() {
		return this.isMoving;
	}

	public int getPixelStepSize() {
		return pixelStepSize;
	}

	public void setMoving(boolean b) {
		this.isMoving = b;
	}

	public void setPixelStepSize(int stepsize) {
		this.pixelStepSize = stepsize;

	}

	public void setMoveFinishedFlagNo(int flagNo) {
		this.finishFlagNo = flagNo;
	}

	public int getMoveFinishedFlagNo() {
		return finishFlagNo;
	}

	public void setCurrentViewCel(int celNo) {
		this.currentViewCel = celNo;

	}

	public void setCurrentViewLoop(int loopNo) {
		this.currentViewLoop = loopNo;
	}

	public boolean shouldBeUpdated() {
		return shouldBeUpdated;
	}

	public void setShouldBeUpdated(boolean b) {
		this.shouldBeUpdated = b;
	}

	public void setLoopFinishedFlag(int finishFlagNo2) {
		this.loopFinishedFlagNo = finishFlagNo2;
		
	}

	public void setInSingleLoop(boolean b) {
		this.inSingleLoop = b;
	}

	public boolean isInSingleloop() {
		return this.inSingleLoop;
	}

	public int getSingleLoopFinishFlag() {
		return loopFinishedFlagNo;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setMovingToDestination(boolean b) {
		this.movingToDestination = b;
	}

	public boolean isMovingToDestination() {
		return this.movingToDestination;
	}

	public void setIsCycling(boolean b) {
		this.isCycling = b;
	}

	public boolean isCycling() {
		return isCycling;
	}

	public boolean isEgo() {
		return ego;
	}

	public void setEgo(boolean b) {
		ego = b;
	}
	
	public void setSingleLoopDirection(LoopDirection loop) {
		this.singleLoopDirection = loop;
	}
	
	public LoopDirection getSingleLoopDirection() {
		return this.singleLoopDirection;
	}

	public void setNumber(int objNo) {
		this.number = objNo;
	}

	public void setReverseCycle(boolean b) {
		this.reverseCycle = b;
	}

	public boolean isReverseCycle() {
		return reverseCycle;
	}

	public void setIgnoreBlocks(boolean b) {
		this.ignoreBlocks = b;
		
	}

	public boolean getIgnoreBlocks() {
		return ignoreBlocks;
	}

	public void setPriority(int prio) {
		this.prio  = prio;
		
	}

	public int getPrio() {
		return prio;
	}

	public int getNumber() {
		return number;
	}

	public Position getDestination() {
		return destination;
	}

	public boolean getObserveObjects() {
		return observeObjects;
	}

	public void setPosition(Position newPos) {
		this.currentPosition = newPos;
	}

	public void setDestination(Position position) {
		this.destination = position;
	}

}
