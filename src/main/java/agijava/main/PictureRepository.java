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
			try {
				Resource res = ResourceReaderFactory.getInstance(resourceReference).read();
				PictureFactory reader = new PictureFactory(res,new PictureCommandFactory());
				Picture pic = reader.getPicture();		
				allPics.put(resourceReference.getEntryNumber(),pic);
			} catch (Exception e) {
				System.err.println("TODO: Handle incorrect logic better!");
			}
		}
	}

	public Picture getPicture(int picNo) {
		Picture requestedLogic = allPics.get(picNo);
		return requestedLogic;
	}
	

}
