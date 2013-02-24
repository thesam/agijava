package agijava.io;

import java.util.List;

import agijava.view.impl.Cel;

public class CelFactory {

	public Cel getCel(List<Integer> rawData, int loopNo, Integer celOffset) {
		int currentOffset = celOffset;
		
		int width = rawData.get(currentOffset++) & 0xff;
		int height = rawData.get(currentOffset++) & 0xff;
		int transparencyAndMirroring = rawData.get(currentOffset++);
		
		int transparentColor = transparencyAndMirroring & 0xf;
		int mirroring = (transparencyAndMirroring & 0xf0) >> 4;
		boolean isMirrored = (mirroring & 8) != 0;
		int mirrorLoop = (mirroring & 7);
		Cel cel = new Cel(width, height,transparentColor);
		int currentData = 1;
		int numberOfScannedLines = 0;
		while (currentData != 0 || numberOfScannedLines < height) {
			currentData = rawData.get(currentOffset++);
			if (currentData != 00) {
				int colorNumber = (currentData >> 4) & 0x0f;
				int length = currentData & 0x0f;
				cel.appendPixelsToRow(numberOfScannedLines, colorNumber, length);
			} else {
				numberOfScannedLines++;
			}
		}
		if (isMirrored && mirrorLoop != loopNo) {
			cel = createMirrorCel(cel);
		}
		cel.fillAllEmptyPixelsWithTransparency();
		return cel;
	}
	
	private Cel createMirrorCel(Cel cel) {
		Cel mirrorCel = new Cel(cel.getWidth(), cel.getHeight(),cel.getTransparency());
		for (int x = 0; x < cel.getWidth(); x++) {
			for (int y = 0; y < cel.getHeight(); y++) {
				mirrorCel.setPixel(cel.getWidth() - 1 - x, y,
						cel.getPixel(x, y));
			}
		}
		return mirrorCel;
	}

}
