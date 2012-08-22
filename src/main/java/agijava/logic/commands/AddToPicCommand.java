package agijava.logic.commands;

import agijava.main.IGameState;

public class AddToPicCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
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
		gameState.addBackgroundViewToBuffer(viewNo,loopNo,celNo,x,y,priority,margin);

	}

	protected Integer getMargin(IGameState gameState) {
		return args.get(6);
	}

	protected Integer getPrio(IGameState gameState) {
		return args.get(5);
	}

	protected Integer getY(IGameState gameState) {
		return args.get(4);
	}

	protected Integer getX(IGameState gameState) {
		return args.get(3);
	}

	protected Integer getCelNo(IGameState gameState) {
		return args.get(2);
	}

	protected Integer getLoopNo(IGameState gameState) {
		return args.get(1);
	}

	protected Integer getViewNo(IGameState gameState) {
		return args.get(0);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 7;
	}

}
