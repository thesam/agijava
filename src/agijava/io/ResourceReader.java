package agijava.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class ResourceReader {
	
	protected List<Integer> rawdata;
	protected final ResourceReference resourceReference;
	
	public ResourceReader(ResourceReference resourceReference) throws IOException {
		this.resourceReference = resourceReference;
		readResourceFromFile(resourceReference, "resource/sq2/vol.");
	}

	@SuppressWarnings("unused")
	private void readResourceFromFile(ResourceReference resourceReference, String base)
			throws FileNotFoundException, IOException {
		rawdata = new ArrayList<Integer>();
		FileInputStream inputStream = new FileInputStream(base + resourceReference.getVolNumber());
		inputStream.skip(resourceReference.getOffset());
		int signature1 = inputStream.read();
		int signature2 = inputStream.read();
		int volNumber = inputStream.read();
		int length1 = inputStream.read();
		int length2 = inputStream.read();
		int length = length2 << 8 | length1;
//		System.err.println("Arraylen: " + length);
		byte[] rawdata = new byte[length];
		inputStream.read(rawdata);
		for (byte b : rawdata) {
			this.rawdata.add((int)b);
		}
	}
	
}
