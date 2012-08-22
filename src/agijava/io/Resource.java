package agijava.io;

import java.util.List;

public class Resource {

	private final List<Integer> rawdata;
	private final int entryNumber;

	public Resource(List<Integer> rawdata, int entryNumber) {
		this.rawdata = rawdata;
		this.entryNumber = entryNumber;
	}

	public List<Integer> getRawData() {
		return rawdata;
	}

	public int getEntryNumber() {
		return entryNumber;
	}

}
