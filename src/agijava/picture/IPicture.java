package agijava.picture;

public interface IPicture {

//	int[][] getPictureData();
	
	public int getPictureColorAt(int x, int y);

	int getWidth();

	int getHeight();

	// int[][] getPriorityData();

	/**
	 * Gets the priority of a certain pixel to be used when considering if a
	 * view should be drawn over it or not. Calculates the priority for pixels
	 * with "special" priority, like signal lines.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getPrioForDrawing(int x, int y);

	int getPrioColorAt(int i, int bottomLeftY);

	void setPriorityColor(int currentByte);

	void setPriorityDrawingEnabled(boolean b);

	void setPictureDrawingEnabled(boolean b);

	void setPictureColor(int currentByte);

	void drawLine(int startX, int startY, int i, int j);

	void drawPixel(int x, int y);

	void fillFrom(int nextX, int nextY);

}
