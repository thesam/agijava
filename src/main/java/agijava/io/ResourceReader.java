package agijava.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ResourceReader {
	
	private ResourceReference resourceReference;
	private String base;
	
	public ResourceReader(ResourceReference resourceReference, String volBase) {
		this.resourceReference = resourceReference;
		this.base = volBase;
	}

	public Resource read()
			throws FileNotFoundException, IOException {
		List<Integer> rawdata = new ArrayList<Integer>();
		FileInputStream inputStream = new FileInputStream(base + resourceReference.getVolNumber());
		inputStream.skip(resourceReference.getOffset());
		@SuppressWarnings("unused")
		int signature1 = inputStream.read();
		@SuppressWarnings("unused")
		int signature2 = inputStream.read();
		@SuppressWarnings("unused")
		int volNumber = inputStream.read();
		int length1 = inputStream.read();
		int length2 = inputStream.read();
		int length = length2 << 8 | length1;
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
