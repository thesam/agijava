package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;
import agijava.main.Position;

public class AddToPicCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
//		a (va): number of the VIEW resource;
//		b (vb): loop number;
//		c (vc): cel number;
//		d (vd): x coordinate;
//		e (ve): y coordinate;
//		f (vf): priority;
//		g (vg): margin.
		int viewNo = getViewNo(gameState);
		int loopNo = getLoopNo(gameState);
		int celNo = getCelNo(gameState);
		int x = getX(gameState);
		int y = getY(gameState);
		int priority = getPrio(gameState);
		int margin = getMargin(gameState);
		addBackgroundViewToBuffer(viewNo,loopNo,celNo,x,y,priority,margin,gameState);

	}

	protected Integer getMargin(GameState gameState) {
		return args.get(6);
	}

	protected Integer getPrio(GameState gameState) {
		return args.get(5);
	}

	protected Integer getY(GameState gameState) {
		return args.get(4);
	}

	protected Integer getX(GameState gameState) {
		return args.get(3);
	}

	protected Integer getCelNo(GameState gameState) {
		return args.get(2);
	}

	protected Integer getLoopNo(GameState gameState) {
		return args.get(1);
	}

	protected Integer getViewNo(GameState gameState) {
		return args.get(0);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 7;
	}
	
	private void addBackgroundViewToBuffer(int viewNo, int loopNo, int celNo,
			int x, int y, int priority, int margin, GameState gameState) {
		AnimatedObject animatedObject = new AnimatedObject(gameState.viewRepository);
		animatedObject.setView(viewNo);
		animatedObject.setCurrentViewLoop(loopNo);
		animatedObject.setCurrentViewCel(celNo);
		animatedObject.setPosition(new Position(x,y));
		animatedObject.setPriority(priority);
		gameState.bufferBackgroundViews.add(animatedObject);
	}

}
