package agijava.main.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.io.PictureFactory;
import agijava.io.Resource;
import agijava.io.ResourceReaderFactory;
import agijava.io.ResourceReference;
import agijava.main.IPictureRepository;
import agijava.picture.IPicture;
import agijava.picture.impl.PictureCommandFactory;

public class PictureRepository implements IPictureRepository {
	private Map<Integer,IPicture> allPics;

	public PictureRepository(List<ResourceReference> pictureReferences) {
		allPics = new HashMap<Integer,IPicture>();
		for (ResourceReference resourceReference : pictureReferences) {
			try {
				Resource res = ResourceReaderFactory.getInstance(resourceReference).read();
				PictureFactory reader = new PictureFactory(res,new PictureCommandFactory());
				IPicture pic = reader.getPicture();		
				allPics.put(resourceReference.getEntryNumber(),pic);
			} catch (Exception e) {
				System.err.println("TODO: Handle incorrect logic better!");
			}
		}
	}

	@Override
	public IPicture getPicture(int picNo) {
		IPicture requestedLogic = allPics.get(picNo);
		return requestedLogic;
	}
	

}
