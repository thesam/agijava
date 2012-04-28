package agijava.io;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

public class ResourceDirTest {

	private static final int EOF = -1;
	private ResourceDir resourceDir;
	private InputStream stream;
	private List<ResourceReference> resourceReferences;

	@Test
	public void canParseOneReference() throws Exception {
		// aFileWithOneResourceReference();
		anInputStream();
		oneResourceReferenceEndingWithEOF();

		aResourceDir();

		resourceReferencesAreGotten();

		assertEquals(1, resourceReferences.size());
		ResourceReference resourceReference = resourceReferences.get(0);
		assertEquals(0, resourceReference.getEntryNumber());
		assertEquals(1, resourceReference.getVolNumber());
		assertEquals(1, resourceReference.getOffset());
	}

	private void oneResourceReferenceEndingWithEOF() throws IOException {
		when(stream.read()).thenReturn(0x10).thenReturn(0x00).thenReturn(0x01).thenReturn(EOF);
	}

	private void anInputStream() {
		stream = mock(InputStream.class);
	}

	@Test
	public void canParseTwoReferences() throws Exception {
		anInputStream();
		twoResourceReferencesEndingInEOF();

		aResourceDir();

		resourceReferencesAreGotten();

		assertEquals(2, resourceReferences.size());
	}

	private void twoResourceReferencesEndingInEOF() throws IOException {
		when(stream.read()).thenReturn(0x10).thenReturn(0x00).thenReturn(0x01).thenReturn(0x10).thenReturn(0x00).thenReturn(0x01).thenReturn(EOF);
	}

	@Test
	public void ignoresLastBytesIfNotDividableByThree() throws Exception {
		anInputStream();
		oneReferenceWithTwoJunkBytesEndingInEOF();
		aResourceDir();

		resourceReferencesAreGotten();

		assertEquals(1, resourceReferences.size());

	}

	private void oneReferenceWithTwoJunkBytesEndingInEOF() throws IOException {
		when(stream.read()).thenReturn(0x10).thenReturn(0x00).thenReturn(0x01).thenReturn(99).thenReturn(99).thenReturn(EOF);
	}

	private void resourceReferencesAreGotten() throws IOException {
		resourceReferences = resourceDir.getResourceReferences();
	}

	private void aResourceDir() {
		resourceDir = new ResourceDir(stream);
	}
}
