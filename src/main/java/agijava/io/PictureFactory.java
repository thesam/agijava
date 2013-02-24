package agijava.io;

import java.io.IOException;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;
import agijava.picture.Picture;
import agijava.picture.PictureCommandFactory;

public class PictureFactory {
	

	private final Resource res;
	private PictureCommandFactory pictureCommandFactory;

	public PictureFactory(Resource res,PictureCommandFactory pictureCommandFactory)
			throws IOException {
				this.res = res;
				this.pictureCommandFactory = pictureCommandFactory;
	}
	
	public IPicture getPicture() {
		Picture picture = new Picture();
		IPictureCommand currentCommand = null;
		for (int i = 0; i < res.getRawData().size(); i++) {
			int currentByte = (0xff & res.getRawData().get(i));
			if (currentByte == 0xff) {
				break;
			}
			if (pictureCommandFactory.isCommandNumber(currentByte)) {
				currentCommand = pictureCommandFactory.getPictureCommand(currentByte);
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
