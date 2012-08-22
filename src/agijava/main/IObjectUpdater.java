package agijava.main;

public interface IObjectUpdater {

	void updateLoopAndCel(IAnimatedObject movingObject);

	void updateSingleLoop(IAnimatedObject movingObject);

	void updatePosition(IAnimatedObject movingObject);

	void stopIfDestinationIsReached(IAnimatedObject movingObject);

}
