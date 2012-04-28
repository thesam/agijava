package agijava.main.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.io.PictureBuilder;
import agijava.io.Resource;
import agijava.io.ResourceReader;
import agijava.io.ResourceReference;
import agijava.main.IPictureRepository;
import agijava.picture.IPicture;

public class PictureRepository implements IPictureRepository {
	private Map<Integer,IPicture> allPics;

	public PictureRepository(List<ResourceReference> pictureReferences) {
		allPics = new HashMap<Integer,IPicture>();
		for (ResourceReference resourceReference : pictureReferences) {
			try {
				Resource res = new ResourceReader(resourceReference).read();
				PictureBuilder reader = new PictureBuilder(res);
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
