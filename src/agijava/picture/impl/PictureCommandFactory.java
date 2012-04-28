package agijava.picture.impl;

import agijava.picture.IPictureCommand;


public class PictureCommandFactory {

	private static final int PLOT_WITH_PEN = 0xfa;
	private static final int CHANGE_PEN_SIZE_AND_STYLE = 0xf9;
	private static final int FILL = 0xf8;
	private static final int RELATIVE_LINE = 0xf7;
	private static final int ABSOLUTE_LINE = 0xf6;
	private static final int DRAW_X_CORNER = 0xf5;
	private static final int DRAW_Y_CORNER = 0xf4;
	private static final int DISABLE_PRIORITY_DRAW = 0xf3;
	private static final int CHANGE_PRIORITY_COLOR_AND_ENABLE_PRIORITY_DRAW = 0xf2;
	private static final int DISABLE_PICTURE_DRAW = 0xf1;
	private static final int CHANGE_PICTURE_COLOR_AND_ENABLE_PICTURE_DRAW = 0xf0;

	public static IPictureCommand getPictureCommand(int currentByte) {
		switch ((int) currentByte) {
		case CHANGE_PICTURE_COLOR_AND_ENABLE_PICTURE_DRAW:
			return new ChangePictureColorCommand();
		case DISABLE_PICTURE_DRAW:
			return new DisablePictureCommand();
		case CHANGE_PRIORITY_COLOR_AND_ENABLE_PRIORITY_DRAW:
			return new ChangePriorityColorCommand();
		case DISABLE_PRIORITY_DRAW:
			return new DisablePriorityDrawCommand();
		case DRAW_Y_CORNER:
			return new DrawYCornerCommand();
		case DRAW_X_CORNER:
			return new DrawXCornerCommand();
		case ABSOLUTE_LINE:
			return new AbsoluteLineCommand();
		case RELATIVE_LINE:
			return new RelativeLineCommand();
		case FILL:
			return new FillCommand();
		case CHANGE_PEN_SIZE_AND_STYLE:
			return new ChangePenCommand();
		case PLOT_WITH_PEN:
			return new PlotWithPenCommand();
		default:
			return null;
		}
	}

	public static boolean isCommandNumber(int currentByte) {
		return (currentByte >= 0xf0);
	}

}
