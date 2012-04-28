package agijava.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ResourceDir {

	private InputStream inputStream;

	public ResourceDir(String path) throws FileNotFoundException {
		inputStream = new FileInputStream(path);
	}

	public ResourceDir(InputStream stream) {
		this.inputStream = stream;
	}

	public List<ResourceReference> getResourceReferences() throws IOException {
		List<ResourceReference> references = new ArrayList<ResourceReference>();
		int firstByte;
		int secondByte;
		int thirdByte;
		int entry = 0;
		do {
			firstByte = inputStream.read();
			secondByte = inputStream.read();
			thirdByte = inputStream.read();
			if (!isEmptyOrEOF(firstByte, secondByte, thirdByte)) {
				int volNumber = (firstByte & 0xf0) >> 4;
				int offset = (firstByte & 0x0f) << 16 | (secondByte << 8)
						| thirdByte;
				ResourceReference reference = new ResourceReference(entry,
						volNumber, offset);
				references.add(reference);
			}
			entry++;
		} while (!isEOF(firstByte,secondByte,thirdByte));
		return references;
	}

	private boolean isEmptyOrEOF(int firstByte, int secondByte, int thirdByte) {
		return isEmptyReference(firstByte, secondByte, thirdByte)
				|| isEOF(firstByte, secondByte, thirdByte);
	}

	private boolean isEmptyReference(int firstByte, int secondByte,
			int thirdByte) {
		return (firstByte == secondByte) && (secondByte == thirdByte)
				&& (thirdByte == 0xff);
	}

	private boolean isEOF(int firstByte, int secondByte, int thirdByte) {
		return (firstByte == -1) || (secondByte == -1) || (thirdByte == -1);
	}
}
