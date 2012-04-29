package agijava.io;

import java.io.IOException;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;
import agijava.picture.impl.Picture;
import agijava.picture.impl.PictureCommandFactory;

public class PictureFactory {
	

	private final Resource res;

	public PictureFactory(Resource res)
			throws IOException {
				this.res = res;
	}
	
	public IPicture getPicture() {
		Picture picture = new Picture();
		IPictureCommand currentCommand = null;
		for (int i = 0; i < res.getRawData().size(); i++) {
			int currentByte = (0xff & res.getRawData().get(i));
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
