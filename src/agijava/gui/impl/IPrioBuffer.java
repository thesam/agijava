package agijava.gui.impl;

public interface IPrioBuffer {

	void reset();

	void drawPixel(int x, int i, int prio);

	int getPixel(int x, int y);

}
