package agijava.view;

public interface ICel extends IDrawable {

	public void appendPixelsToRow(int numberOfScannedLines, int colorNumber,
			int length);

	public void FillAllEmptyPixelsWithTransparency();

	public int getTransparency();

	public void setTransparency(int transparentColor);

	public boolean isTransparentAt(int x, int y);

}
