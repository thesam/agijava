package agijava.picture.impl;

public class PictureReference {

	private int entryNumber;
	private int volNumber;
	private int offset;

	public PictureReference( int entryNumber, int volNumber, int offset) {
		this.setEntryNumber(entryNumber);
		this.setVolNumber(volNumber);
		this.offset = offset;
	}

	public void setEntryNumber(int entryNumber) {
		this.entryNumber = entryNumber;
	}

	public int getEntryNumber() {
		return entryNumber;
	}

	public void setVolNumber(int volNumber) {
		this.volNumber = volNumber;
	}

	public int getVolNumber() {
		return volNumber;
	}

	public long getOffset() {
		return offset;
	}


}
