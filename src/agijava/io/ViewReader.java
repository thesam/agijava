package agijava.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import agijava.view.ICel;
import agijava.view.ILoop;
import agijava.view.IView;
import agijava.view.impl.Cel;
import agijava.view.impl.Loop;
import agijava.view.impl.View;

public class ViewReader {

	private Resource res;

	public ViewReader(Resource res) throws IOException {
		this.res = res;
	}

	@SuppressWarnings("unused")
	public IView getView() {
 		View view = new View(res.getEntryNumber());
		int fileOffset = 0;
		
		List<Integer> loopOffsets = new ArrayList<Integer>();
		int unknown1 = res.getRawData().get(fileOffset++);
		int unknown2 = res.getRawData().get(fileOffset++);
		int numberOfLoops = res.getRawData().get(fileOffset++);
		int description1 = res.getRawData().get(fileOffset++);
		int description2 = res.getRawData().get(fileOffset++);
		for (int i = 0; i < numberOfLoops; i++) {
			int loop1 = res.getRawData().get(fileOffset++)&0xff;
			int loop2 = res.getRawData().get(fileOffset++)&0xff;
			int newOffset = ((loop2<<8)|loop1);
			loopOffsets.add(new Integer(newOffset));			
		}
		
		int loopNo = 0;
		for (Integer loopOffset : loopOffsets) {
			ILoop loop = new Loop();
			int thisOffset = loopOffset;
			List<Integer> celOffsets= new ArrayList<Integer>();
			int numberOfCels = res.getRawData().get(thisOffset++);
			for (int cel = 0; cel < numberOfCels; cel++) {
				int celPos1 = res.getRawData().get(thisOffset++)&0xff;
				int celPos2 = res.getRawData().get(thisOffset++)&0xff;
				int celPos = (celPos2<<8) | celPos1;
				celPos += loopOffset;
				celOffsets.add(celPos);
			}
			for (Integer celOffset : celOffsets) {
				int width = res.getRawData().get(celOffset++) & 0xff;
				int height = res.getRawData().get(celOffset++) & 0xff;
				int transparencyAndMirroring = res.getRawData().get(celOffset++);
				int transparentColor = transparencyAndMirroring & 0xf;
				int mirroring = (transparencyAndMirroring & 0xf0) >> 4;
				boolean isMirrored = (mirroring & 8) != 0;
				int mirrorLoop = (mirroring & 7);
				ICel cel = new Cel(width,height);
				int currentData = 1;
				int numberOfScannedLines= 0;
				while (currentData != 0 || numberOfScannedLines < height) {
					currentData = res.getRawData().get(celOffset++);
					if (currentData != 00) {
						int colorNumber = (currentData >> 4)&0x0f;
						int length = currentData & 0x0f;
						cel.appendPixelsToRow(numberOfScannedLines,colorNumber,length);
					} else {
						numberOfScannedLines++;
					}
				}
				if (isMirrored && mirrorLoop != loopNo) {
					cel = createMirrorCel(cel);
				}
				cel.setTransparency(transparentColor);
				cel.FillAllEmptyPixelsWithTransparency();
				loop.addCel(cel);
			}
			view.addLoop(loop);
			loopNo++;
		}
		return view;
	}

	private Cel createMirrorCel(ICel cel) {
		Cel mirrorCel = new Cel(cel.getWidth(),cel.getHeight());
		for (int x = 0; x < cel.getWidth(); x++) {
			for (int y = 0; y < cel.getHeight(); y++) {
				mirrorCel.setPixel(cel.getWidth()-1-x,y,cel.getPixel(x,y));
			}
		}
		return mirrorCel;
	}

}
