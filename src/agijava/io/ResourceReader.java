package agijava.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ResourceReader {
	
	private ResourceReference resourceReference;
	private String base;
	
	public ResourceReader(ResourceReference resourceReference) throws IOException {
		this.resourceReference = resourceReference;
		this.base = "resource/sq2/vol.";
	}

	public Resource read()
			throws FileNotFoundException, IOException {
		List<Integer> rawdata = new ArrayList<Integer>();
		FileInputStream inputStream = new FileInputStream(base + resourceReference.getVolNumber());
		inputStream.skip(resourceReference.getOffset());
		int signature1 = inputStream.read();
		int signature2 = inputStream.read();
		int volNumber = inputStream.read();
		int length1 = inputStream.read();
		int length2 = inputStream.read();
		int length = length2 << 8 | length1;
//		System.err.println("Arraylen: " + length);
		byte[] rawdataBytes = new byte[length];
		inputStream.read(rawdataBytes);
		for (byte b : rawdataBytes) {
			rawdata.add((int)b);
		}
		return new Resource(rawdata,resourceReference.getEntryNumber());
	}

	public ResourceReference getResourceReference() {
		return resourceReference;
	}

}
