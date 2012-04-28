package agijava.io;

import java.util.List;

public class Resource {

	private List<Integer> rawdata;

	public Resource(List<Integer> rawdata, int entryNumber) {
		this.rawdata = rawdata;
	}

	public List<Integer> getRawData() {
		return rawdata;
	}

	public int getEntryNumber() {
		return 0;
	}

}
