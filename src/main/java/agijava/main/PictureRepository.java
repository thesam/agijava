package agijava.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.io.PictureFactory;
import agijava.io.Resource;
import agijava.io.ResourceReaderFactory;
import agijava.io.ResourceReference;
import agijava.picture.Picture;
import agijava.picture.PictureCommandFactory;

public class PictureRepository {
	private Map<Integer,Picture> allPics;

	public PictureRepository(List<ResourceReference> pictureReferences) {
		allPics = new HashMap<Integer,Picture>();
		for (ResourceReference resourceReference : pictureReferences) {
			int entryNumber = resourceReference.getEntryNumber();
			try {
				Resource res = ResourceReaderFactory.getInstance(resourceReference).read();
				PictureFactory reader = new PictureFactory(res,new PictureCommandFactory());
				Picture pic = reader.getPicture();		
				allPics.put(entryNumber,pic);
			} catch (Exception e) {
				Logger.warn("Failed to load picture " + entryNumber);
			}
		}
	}

	public Picture getPicture(int picNo) {
		Picture requestedLogic = allPics.get(picNo);
		return requestedLogic;
	}
	

}
