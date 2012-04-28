package agijava.picture.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PicDir {

	private FileInputStream inputStream;

	public PicDir(String path) throws FileNotFoundException {
		inputStream = new FileInputStream(path);
	}

	public List<PictureReference> getPictureReferences() throws IOException {
		List<PictureReference> pictureReferences = new ArrayList<PictureReference>();
		int firstByte;
		int secondByte;
		int thirdByte;
		int entry = 0;
		do {
			firstByte = inputStream.read();
			secondByte = inputStream.read();
			thirdByte = inputStream.read();
			if (!isEmptyOrEOF(firstByte,secondByte,thirdByte)) {
				int volNumber = (firstByte & 0xf0) >> 4;
				int offset = (firstByte & 0x0f) << 16 | (secondByte<< 8) | thirdByte;
				PictureReference pictureReference = new PictureReference(entry, volNumber,
						offset);
				pictureReferences.add(pictureReference);
				entry++;
			}
		} while (firstByte != -1 && secondByte != -1 && thirdByte != -1);
		return pictureReferences;
	}

	private boolean isEmptyOrEOF(int firstByte, int secondByte, int thirdByte) {
		return (firstByte == secondByte)&& (secondByte == thirdByte) && (thirdByte == 0xff) | (firstByte == -1) | (secondByte == -1) | (thirdByte == -1);
	}

}
