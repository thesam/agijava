package agijava.io;

import java.util.ArrayList;
import java.util.List;

public class RawByteArray {

	private List<Integer> rawdata;
	private int offset;
	private int stopOffset;

	public RawByteArray(List<Integer> rawdata, int offset) {
		this.rawdata = rawdata;
		this.offset = offset;
	}

	public RawByteArray() {
		this(new ArrayList<Integer>(), 0);

	}

	public RawByteArray(List<Integer> rawdata) {
		this(rawdata, 0);
	}

	public RawByteArray(int[] foo) {
		rawdata = new ArrayList<Integer>();
		for (int integer : foo) {
			rawdata.add(integer);
		}
	}

	public int getNextAndStep() {
		int retVal = rawdata.get(offset);
		offset++;
		// System.err.println("Stepping to: " + offset);
		// if (retVal != -1) {
		return retVal & 0xff;
		// } else {
		// return -1;
		// }
	}

	public int getNextOffsetToBeRead() {
		return offset;
	}

	public List<Integer> getBytes(int numberOfArgs) {
		List<Integer> ret = new ArrayList<Integer>();
		int i = 0;
		while (i < numberOfArgs) {
			ret.add(getNextAndStep());
			i++;
		}
		return ret;
	}

	public int getSize() {
		return rawdata.size();
	}

	public void stepBack() {
		stepBack(1);
	}

	public void setStopOffset(int messageOffset) {
		this.stopOffset = messageOffset;
	}

	public int getStopOffset() {
		return stopOffset;
	}

	public RawByteArray getSubsetClone(int ifLength) {
		RawByteArray subset = new RawByteArray(rawdata);
		subset.setOffset(this.offset);
		subset.setStopOffset(offset + ifLength);
		return subset;
	}

	public void setOffset(int offset2) {
		offset = offset2;
	}

	public void step(int ifLength) {
		offset += ifLength;
	}

	public void stepBack(int i) {
		offset -= i;
	}

	public void moveStopOffsetBack(int i) {
		stopOffset -= i;

	}

	public int getByteAtOffset(int i) {
		return rawdata.get(i);
	}

	public void moveStopOffsetForward(int elseOffset) {
		stopOffset += elseOffset;

	}
	
	public boolean hasMoreBytes() {
		return getNextOffsetToBeRead() < rawdata.size();
	}

}
