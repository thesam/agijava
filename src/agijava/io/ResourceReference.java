package agijava.io;

public class ResourceReference {

	private final int entryNumber;
	private final int volNumber;
	private final int offset;

	public ResourceReference( int entryNumber, int volNumber, int offset) {
		this.entryNumber = entryNumber;
		this.volNumber =volNumber;
		this.offset = offset;
	}

	public int getEntryNumber() {
		return entryNumber;
	}

	public int getVolNumber() {
		return volNumber;
	}

	public long getOffset() {
		return offset;
	}


}
