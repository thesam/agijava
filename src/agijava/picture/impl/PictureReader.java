package agijava.picture.impl;

import java.io.IOException;

import agijava.io.ResourceReader;
import agijava.io.ResourceReference;
import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;

public class PictureReader extends ResourceReader {
	

	public PictureReader(ResourceReference resourceReference)
			throws IOException {
		super(resourceReference);
//		System.out.println(rawdata.size());
	}
	
	public IPicture getPicture() {
		Picture picture = new Picture();
		IPictureCommand currentCommand = null;
		for (int i = 0; i < rawdata.size(); i++) {
			int currentByte = (0xff & rawdata.get(i));
			if (currentByte == 0xff) {
				break;
			}
			if (PictureCommandFactory.isCommandNumber(currentByte)) {
				currentCommand = PictureCommandFactory.getPictureCommand(currentByte);
				if (!currentCommand.needsArguments()) {
					currentCommand.run(picture,currentByte);
				}
				continue;
			} else {
				currentCommand.run(picture,currentByte);
			}
			
		}
		return picture;
	}

}
