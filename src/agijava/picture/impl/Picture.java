package agijava.picture.impl;

import java.util.LinkedList;
import java.util.Queue;

import agijava.picture.IPicture;

public class Picture implements IPicture {

	public static final int PRIORITY_BLOCK = 0;
	public static final int PRIORITY_CONDITIONAL_BLOCK = 1;
	public static final int PRIORITY_TRIGGER = 2;

	private static final int PICTURE_Y = 168;
	private static final int PICTURE_X = 160;
	
	private static final int DEFAULT_PICTURE_COLOR = 15;
	private static final int DEFAULT_PRIORITY_COLOR = 4;
	private static final int LOWEST_NORMAL_PRIO = 4;

	private final int pictureData[][];
	private final int priorityData[][];

	private int pictureColor;
	private int priorityColor;

	private boolean pictureDrawingEnabled;
	private boolean priorityDrawingEnabled;

	public Picture() {
		pictureData = new int[PICTURE_X][PICTURE_Y];
		priorityData = new int[PICTURE_X][PICTURE_Y];
		setDefaultPictureColors();
	}

	public void setPictureColor(int currentByte) {
		this.pictureColor = currentByte;

	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		// System.err.println("Draw from " + x1+":"+y1+" to " + x2+":"+y2);
		int height, width;
		float x, y, addX, addY;

		height = (y2 - y1);
		width = (x2 - x1);
		addX = (height == 0 ? height : (float) width / Math.abs(height));
		addY = (width == 0 ? width : (float) height / Math.abs(width));

		if (Math.abs(width) > Math.abs(height)) {
			y = y1;
			addX = (width == 0 ? 0 : (width / Math.abs(width)));
			for (x = x1; x != x2; x += addX) {
				drawPixel(lineRound(x, addX), lineRound(y, addY));
				y += addY;
			}
			drawPixel(x2, y2);
		} else {
			x = x1;
			addY = (height == 0 ? 0 : (height / Math.abs(height)));
			for (y = y1; y != y2; y += addY) {
				drawPixel(lineRound(x, addX), lineRound(y, addY));
				x += addX;
			}
			drawPixel(x2, y2);
		}
	}

	public void drawPixel(int x2, int y2) {
		if (pictureDrawingEnabled) {
			pictureData[x2][y2] = pictureColor;
		}
		if (priorityDrawingEnabled) {
			priorityData[x2][y2] = priorityColor;
		}

	}

	public void setPictureDrawingEnabled(boolean b) {
		this.pictureDrawingEnabled = b;

	}

	public void setPriorityDrawingEnabled(boolean b) {
		this.priorityDrawingEnabled = b;

	}

	public void setPriorityColor(int currentByte) {
		this.priorityColor = currentByte;
	}

	public void fillFrom(int x, int y) {
		Queue<PicturePixel> q = new LinkedList<PicturePixel>();
		if (isUnfilled(x, y)) {
			addAndChangeColor(q, new PicturePixel(x, y));
			while (!q.isEmpty()) {
				PicturePixel elem = q.poll();
				x = elem.x;
				y = elem.y;
				PicturePixel dirs[] = new PicturePixel[4];
				dirs[0] = new PicturePixel(x, y - 1);
				dirs[1] = new PicturePixel(x - 1, y);
				dirs[2] = new PicturePixel(x + 1, y);
				dirs[3] = new PicturePixel(x, y + 1);
				for (PicturePixel picturePixel : dirs) {
					if (isUnfilled(picturePixel.x, picturePixel.y)) {
						addAndChangeColor(q, picturePixel);
					}

				}

			}
		} else {
			return;
		}
	}

	@Override
	public int getWidth() {
		return PICTURE_X;
	}

	@Override
	public int getHeight() {
		return PICTURE_Y;
	}

	@Override
	public int getPrioForDrawingAt(int x, int y) {
//		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
//			return 0;
//		}
		int prio = priorityData[x][y];
		while (prio < LOWEST_NORMAL_PRIO) {
			y++;
			if (y >= priorityData[x].length) {
				return -1;
			}
			prio = priorityData[x][y];
		}
		return prio;
	}

	@Override
	public int getPrioColorAt(int x, int y) {
//		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
//			return 0;
//		}
		int prio = priorityData[x][y];
		return prio;
	}

	@Override
	public int getPictureColorAt(int x, int y) {
		return pictureData[x][y];
	}

	private void setDefaultPictureColors() {
		for (int x = 0; x < PICTURE_X; x++) {
			for (int y = 0; y < PICTURE_Y; y++) {
				pictureData[x][y] = DEFAULT_PICTURE_COLOR;
				priorityData[x][y] = DEFAULT_PRIORITY_COLOR;
			}
		}

	}

	private boolean isUnfilled(int x, int y) {
		if (x >= 0 && x < PICTURE_X && y >= 0 && y < PICTURE_Y) {
			if (pictureDrawingEnabled) {
				if (pictureData[x][y] == DEFAULT_PICTURE_COLOR) {
					return true;
				}
			} else if (priorityDrawingEnabled) {
				if (priorityData[x][y] == DEFAULT_PRIORITY_COLOR) {
					return true;
				}
			}
		}
		return false;
	}

	private void addAndChangeColor(Queue<PicturePixel> q, PicturePixel pp) {
		drawPixel(pp.x, pp.y);
		q.add(pp);
	}

	private int lineRound(float aNumber, float dirn) {
		if (dirn < 0) {
			return (int) ((aNumber - Math.floor(aNumber) <= 0.501) ? Math
					.floor(aNumber) : Math.ceil(aNumber));
		}
		return (int) ((aNumber - Math.floor(aNumber) < 0.499) ? Math
				.floor(aNumber) : Math.ceil(aNumber));
	}
}
