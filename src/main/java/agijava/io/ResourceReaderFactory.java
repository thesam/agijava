package agijava.io;

public class ResourceReaderFactory {

	public ResourceReader getInstance(ResourceReference resourceReference) {
		return new ResourceReader(resourceReference, "resource/sq2/vol.");
	}

}
