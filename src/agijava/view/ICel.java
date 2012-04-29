package agijava.view;

public interface ICel extends IDrawable {

	public void appendPixelsToRow(int numberOfScannedLines, int colorNumber,
			int length);

	public void FillAllEmptyPixelsWithTransparency();

	public int getTransparency();

	public boolean isTransparentAt(int x, int y);

}
