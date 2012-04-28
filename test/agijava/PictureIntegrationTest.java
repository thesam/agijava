package agijava;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import agijava.gui.IGraphicsDevice;
import agijava.gui.impl.GameGui;
import agijava.gui.impl.GraphicsDeviceFactory;
import agijava.gui.impl.PrioBuffer;
import agijava.gui.impl.PrioCalculator;
import agijava.gui.impl.SwingGraphicsFrame;
import agijava.io.ResourceDir;
import agijava.io.ResourceReference;
import agijava.main.impl.PictureRepository;
import agijava.main.impl.Text;
import agijava.picture.IPicture;

@SuppressWarnings("unused")
public class PictureIntegrationTest {
	@Ignore
	public void showPictureInGui() throws Exception {
		ResourceDir picDir = new ResourceDir("resource/sq2/picdir");
		List<ResourceReference> pictureReferences = picDir
				.getResourceReferences();
		PictureRepository pictureRepository = new PictureRepository(
				pictureReferences);
		IGraphicsDevice graphics = GraphicsDeviceFactory.createSwingGraphicsDevice();
		GameGui gui = new GameGui(graphics, new PrioCalculator(),
				new PrioBuffer(320, 200));
		IPicture picture = pictureRepository.getPicture(5);
		gui.drawPicture(picture);
		gui.printText(new Text(0, 0, "0-0"));
		gui.printText(new Text(1, 0, "1-0"));
		gui.printText(new Text(2, 0, "2-0"));
		gui.printText(new Text(3, 0, "3-0"));
		gui.printText(new Text(4, 0, "4-0"));
		gui.printText(new Text(5, 0, "5-0"));
		gui.printText(new Text(6, 0, "6-0"));
		gui.printText(new Text(7, 0, "7-0"));
		gui.printText(new Text(8, 0, "8-0"));
		gui.printText(new Text(9, 0, "9-0"));
		gui.printText(new Text(10, 0, "10-0"));
		gui.printText(new Text(11, 0, "11-0"));
		gui.printText(new Text(12, 0, "12-0"));
		gui.printText(new Text(13, 0, "13-0"));
		gui.printText(new Text(14, 0, "14-0"));
		gui.printText(new Text(15, 0, "15-0"));
		gui.printText(new Text(16, 0, "16-0"));
		gui.printText(new Text(17, 0, "17-0"));
		gui.printText(new Text(18, 0, "18-0"));
		gui.printText(new Text(19, 0, "19-0"));
		gui.printText(new Text(20, 0, "20-0"));
		gui.printText(new Text(21, 0, "21-0"));
		gui.printText(new Text(22, 0, "22-0"));
		gui.printText(new Text(23, 0, "23-0"));
		gui.printText(new Text(24, 0, "24-0"));
		gui.drawTextDialog("HELLO!\nFRIEND!");
		while (0 < 100) {
			gui.updateToScreen();
		}
//		int i = 5;
	}
}
