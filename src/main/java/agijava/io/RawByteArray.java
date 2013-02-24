package agijava.io;

import java.util.ArrayList;
import java.util.List;

public class RawByteArray {

	private List<Integer> rawdata;
	private int currentOffset;
	private int stopOffset;

	public RawByteArray(List<Integer> rawdata, int offset) {
		this.rawdata = rawdata;
		this.currentOffset = offset;
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
		int retVal = rawdata.get(currentOffset);
		currentOffset++;
		return retVal & 0xff;
	}
	
	public int getNextUInt16AndStep() {
		int offset1 = getNextAndStep();
		int offset2 = getNextAndStep();
		int offset = (offset2 << 8) | offset1;
		return offset;
	}

	public int getNextOffsetToBeRead() {
		return currentOffset;
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

	public RawByteArray getSubsetClone(int ifLength) {
		RawByteArray subset = new RawByteArray(rawdata);
		subset.setOffset(this.currentOffset);
		subset.setStopOffset(currentOffset + ifLength);
		return subset;
	}

	public void setOffset(int offset2) {
		currentOffset = offset2;
	}

	public void step(int ifLength) {
		currentOffset += ifLength;
	}

	public void stepBack(int i) {
		currentOffset -= i;
	}

	public int getByteAtOffset(int i) {
		return rawdata.get(i);
	}

	public boolean hasMoreBytes() {
		return getNextOffsetToBeRead() < rawdata.size();
	}

	public void setStopOffset(int messageOffset) {
		this.stopOffset = messageOffset;
	}

	public int getStopOffset() {
		return stopOffset;
	}

}
