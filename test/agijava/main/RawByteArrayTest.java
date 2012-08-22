package agijava.main;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agijava.io.RawByteArray;


public class RawByteArrayTest {
	private RawByteArray raw;

	@Test
	public void canReturnOneStoredByte() throws Exception {
		List<Integer> rawData = new ArrayList<Integer>();
		rawData.add(1);
		aRawByteArray(rawData);
		assertEquals(1,raw.getNextAndStep());
	}

	private void aRawByteArray(List<Integer> rawData) {
		raw = new RawByteArray(rawData);
	}
	
	
}
