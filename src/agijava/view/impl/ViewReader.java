package agijava.view.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import agijava.io.ResourceReader;
import agijava.io.ResourceReference;
import agijava.view.ICel;
import agijava.view.ILoop;
import agijava.view.IView;

public class ViewReader extends ResourceReader {

	public ViewReader(ResourceReference resourceReference) throws IOException {
		super(resourceReference);
	}

	@SuppressWarnings("unused")
	public IView getView() {
 		View view = new View(resourceReference.getEntryNumber());
		int fileOffset = 0;
		
		List<Integer> loopOffsets = new ArrayList<Integer>();
		int unknown1 = rawdata.get(fileOffset++);
		int unknown2 = rawdata.get(fileOffset++);
		int numberOfLoops = rawdata.get(fileOffset++);
		int description1 = rawdata.get(fileOffset++);
		int description2 = rawdata.get(fileOffset++);
		for (int i = 0; i < numberOfLoops; i++) {
			int loop1 = rawdata.get(fileOffset++)&0xff;
			int loop2 = rawdata.get(fileOffset++)&0xff;
			int newOffset = ((loop2<<8)|loop1);
			loopOffsets.add(new Integer(newOffset));			
		}
		
		int loopNo = 0;
		for (Integer loopOffset : loopOffsets) {
			ILoop loop = new Loop();
			int thisOffset = loopOffset;
			List<Integer> celOffsets= new ArrayList<Integer>();
			int numberOfCels = rawdata.get(thisOffset++);
			for (int cel = 0; cel < numberOfCels; cel++) {
				int celPos1 = rawdata.get(thisOffset++)&0xff;
				int celPos2 = rawdata.get(thisOffset++)&0xff;
				int celPos = (celPos2<<8) | celPos1;
				celPos += loopOffset;
				celOffsets.add(celPos);
			}
			for (Integer celOffset : celOffsets) {
				int width = rawdata.get(celOffset++) & 0xff;
				int height = rawdata.get(celOffset++) & 0xff;
				int transparencyAndMirroring = rawdata.get(celOffset++);
				int transparentColor = transparencyAndMirroring & 0xf;
				int mirroring = (transparencyAndMirroring & 0xf0) >> 4;
				boolean isMirrored = (mirroring & 8) != 0;
				int mirrorLoop = (mirroring & 7);
				ICel cel = new Cel(width,height);
				int currentData = 1;
				int numberOfScannedLines= 0;
				while (currentData != 0 || numberOfScannedLines < height) {
					currentData = rawdata.get(celOffset++);
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
